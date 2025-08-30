package com.avmakarov.school.web.app.mapper;

import com.avmakarov.school.model.domain.Person;
import com.avmakarov.school.model.domain.SchoolClass;
import com.avmakarov.school.model.domain.Student;
import com.avmakarov.school.model.service.ClassService;
import com.avmakarov.school.web.app.transport.ClassTO;
import com.avmakarov.school.web.app.transport.StudentTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = StudentMapper.class)
public abstract class ClassMapper {

    @Autowired
    public ClassService classService;

    @Named("classTableTo")
    @Mapping(target = "oid", source = "id")
    @Mapping(target = "name")
    public abstract ClassTO.Table tableClass(SchoolClass schoolClass);

    @IterableMapping(qualifiedByName = "classTableTo")
    public abstract List<ClassTO.Table> list(Iterable<SchoolClass> all);

    public SchoolClass domain(ClassTO classTO) {
        SchoolClass schoolClass = null;
        if (classTO.oid() != null) {
            schoolClass = classService.findOne(classTO.oid());
        }
        if (schoolClass == null) {
            schoolClass = new SchoolClass();
        }
        schoolClass.setName(classTO.name());
        mergeStudents(classTO.students(), schoolClass);
        return schoolClass;
    }

    private void mergeStudents(List<StudentTO> students, SchoolClass schoolClass) {
        List<Student> left = new ArrayList<>(schoolClass.getStudents());
        for (StudentTO transport : students) {
            Student student = null;
            for (Student search : left) {
                if (search.getId().equals(transport.oid())) {
                    student = search;
                    break;
                }
            }
            if (student == null) {
                student = new Student();
                student.setPerson(new Person(transport.fullName()));
                student.setSchoolClass(schoolClass);
                schoolClass.getStudents().add(student);
            } else {
                left.remove(student);
                student.getPerson().setFullName(transport.fullName());
            }
        }
        for (Student student : left) {
            schoolClass.getStudents().remove(student);
        }
    }

    @Named("findClass")
    protected SchoolClass findClass(Long id) {
        return classService.findOne(id);
    }

    @Mapping(target = "oid", source = "id")
    @Mapping(target = "name")
    @Mapping(target = "students", source = "students", qualifiedByName = "transportStudents")
    public abstract ClassTO transportClass(SchoolClass save);
}
