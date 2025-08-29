package com.avmakarov.school.web.app.mapper;

import com.avmakarov.school.model.domain.Student;
import com.avmakarov.school.model.domain.Teacher;
import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.service.StudentService;
import com.avmakarov.school.model.service.TeacherService;
import com.avmakarov.school.model.service.UserService;
import com.avmakarov.school.web.app.transport.UserTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses = {StudentMapper.class})
public abstract class UserMapper {

    @Autowired
    private UserService service;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Named("userListTo")
    @Mapping(target = "oid", source = "id")
    @Mapping(target = "username", source = "login")
    @Mapping(target = "info")
    @Mapping(target = "admin")
    @Mapping(target = "active")
    public abstract UserTO.Table userListTo(User domain);

    @Mapping(target = "oid", source = "id")
    @Mapping(target = "username", source = "login")
    @Mapping(target = "info")
    @Mapping(target = "admin")
    @Mapping(target = "active")
    @Mapping(target = "teacher", source = "teacher.id")
    @Mapping(target = "students", source = "students", qualifiedByName = "studentsIds")
    public abstract UserTO transport(User domain);

    @Named("studentsIds")
    protected List<Long> studentsIds(List<Student> domain) {
        return domain.stream().map(Student::getId).toList();
    }

    @IterableMapping(qualifiedByName = "userListTo")
    public abstract List<UserTO.Table> list(Iterable<User> all);

    public User domain(UserTO transport) {
        User domain = null;
        if (transport.oid() != null) {
            domain = service.findOne(transport.oid());
        }
        if (domain == null) {
            domain = new User();
        }
        domain(transport, domain);
        return domain;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "username")
    @Mapping(target = "info")
    @Mapping(target = "admin")
    @Mapping(target = "active")
    @Mapping(target = "teacher", source = "teacher", qualifiedByName = "findTeacher")
    @Mapping(target = "students", source = "students", qualifiedByName = "findStudents")
    protected abstract void domain(UserTO transport, @MappingTarget User domain);

    @Named("findTeacher")
    protected Teacher findTeacher(Long id) {
        return teacherService.findOne(id);
    }

    @Named("findStudents")
    protected List<Student> findStudents(List<Long> transports) {
        return studentService.findAllByIds(transports);
    }

}
