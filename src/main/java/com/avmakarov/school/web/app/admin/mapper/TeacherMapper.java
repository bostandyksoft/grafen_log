package com.avmakarov.school.web.app.admin.mapper;

import com.avmakarov.school.model.domain.Person;
import com.avmakarov.school.model.domain.Teacher;
import com.avmakarov.school.model.service.TeacherService;
import com.avmakarov.school.web.app.admin.transport.TeacherTO;
import jakarta.validation.Valid;
import org.mapstruct.*;

import java.util.List;

@Mapper
public abstract class TeacherMapper {

    @Named("transportTeacher")
    @Mapping(target = "oid", source = "id")
    @Mapping(target = "fullName", source = "person.fullName")
    public abstract TeacherTO transportTeacher(Teacher domain);

    @IterableMapping(qualifiedByName = "transportTeacher")
    public abstract List<TeacherTO> list(Iterable<Teacher> all);

    public Teacher domain(TeacherService service, @Valid TeacherTO transport) {
        Teacher domain = null;
        if (transport.oid() != null) {
            domain = service.findOne(transport.oid());
        }
        if (domain == null) {
            domain = new Teacher();
            Person person = new Person();
            domain.setPerson(person);
        }
        domain(transport, domain);
        return domain;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person.fullName", source = "fullName")
    protected abstract void domain(@Valid TeacherTO transport, @MappingTarget Teacher domain);
}
