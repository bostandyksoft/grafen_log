package com.avmakarov.school.web.mapper;

import com.avmakarov.school.model.domain.Person;
import com.avmakarov.school.model.domain.SchoolClass;
import com.avmakarov.school.model.domain.Student;
import com.avmakarov.school.model.service.ClassService;
import com.avmakarov.school.model.service.StudentService;
import com.avmakarov.school.web.app.admin.transport.StudentTO;
import jakarta.validation.Valid;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public abstract class StudentMapper {

    @Autowired
    private ClassService classService;

    @Named("transportStudent")
    @Mapping(target = "oid", source = "id")
    @Mapping(target = "fullName", source = "person.fullName")
    @Mapping(target = "classId", source = "schoolClass.id")
    public abstract StudentTO transportStudent(Student domain);

    @IterableMapping(qualifiedByName = "transportStudent")
    public abstract List<StudentTO> list(Iterable<Student> all);

    public Student domain(StudentService service, @Valid StudentTO transport) {
        Student domain = null;
        if (transport.oid() != null) {
            domain = service.findOne(transport.oid());
        }
        if (domain == null) {
            domain = new Student();
            Person person = new Person();
            domain.setPerson(person);
        }
        domain(transport, domain);
        return domain;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person.fullName", source = "fullName")
    @Mapping(target = "schoolClass", source = "classId", qualifiedByName = "findClass")
    protected abstract void domain(@Valid StudentTO transport, @MappingTarget Student domain);

    @Named("findClass")
    protected SchoolClass findClass(Long id) {
        if (id == null) {
            return null;
        }
        return classService.findOne(id);
    }
}
