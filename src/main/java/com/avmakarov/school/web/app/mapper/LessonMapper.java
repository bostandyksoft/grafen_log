package com.avmakarov.school.web.app.mapper;

import com.avmakarov.school.model.domain.Lesson;
import com.avmakarov.school.model.domain.LessonStudent;
import com.avmakarov.school.model.domain.Student;
import com.avmakarov.school.model.service.LessonService;
import com.avmakarov.school.model.service.StudentService;
import com.avmakarov.school.model.service.UserService;
import com.avmakarov.school.web.app.transport.LessonTO;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {TeacherMapper.class, SubjectMapper.class, ClassMapper.class})
public abstract class LessonMapper {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    public Lesson domain(@Valid LessonTO transport) {
        Lesson target;
        if (transport.oid() == null) {
            target = new Lesson();
            target.setTeacher(userService.getCurrentUser().getTeacher());
        } else {
            target = lessonService.findOne(transport.oid());
            assert target.getSchoolClass().getId().equals(transport.schoolClass());
            assert target.getSubject().getId().equals(transport.subject());
        }
        domain(transport, target);
        return target;
    }

    @Mapping(target = "id", source = "oid")
    @Mapping(target = "date")
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "subject", source = "subject", qualifiedByName = "findSubject")
    @Mapping(target = "schoolClass", source = "schoolClass", qualifiedByName = "findClass")
    @Mapping(target = "students", expression = "java(mergeStudents(transport.students(), domain))")
    protected abstract void domain(@Valid LessonTO transport, @MappingTarget Lesson domain);

    @Mapping(target = "oid", source = "id")
    @Mapping(target = "date")
    @Mapping(target = "subject", source = "subject.id")
    @Mapping(target = "schoolClass", source = "schoolClass.id")
    public abstract LessonTO transport(Lesson domain);

    @Named("mergeStudents")
    protected List<LessonStudent> mergeStudents(List<LessonTO.LessonStudentTO> transport, Lesson target) {
        List<LessonStudent> left = new ArrayList<>(target.getStudents());

        List<Student> classStudents = studentService.findAllByClass(target.getSchoolClass());

        for (LessonTO.LessonStudentTO studentTO : transport) {
            LessonStudent found = null;
            for (LessonStudent lessonStudent : left) {
                if (lessonStudent.getId().getStudent().getId().equals(studentTO.studentId())) {
                    found = lessonStudent;
                    break;
                }
            }
            if (found != null) {
                left.remove(found);
            } else {
                Student student = studentService.findOne(studentTO.studentId());
                found = new LessonStudent();
                found.setId(new LessonStudent.Id(target, student));
                target.getStudents().add(found);
            }
            classStudents.removeIf(student -> student.getId().equals(studentTO.studentId()));

            found.setState(studentTO.state());
            found.setGrade(studentTO.grade());
            found.setComment(studentTO.comment());
        }
        for (Student student : classStudents) {
            LessonStudent state = new LessonStudent();
            state.setId(new LessonStudent.Id(target, student));
            target.getStudents().add(state);
            state.setState(LessonStudent.State.Unknown);
            state.setGrade(null);
            state.setComment(null);
        }

        return target.getStudents();
    }
}
