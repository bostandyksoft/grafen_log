package com.avmakarov.school.web.app.mapper;

import com.avmakarov.school.model.domain.Subject;
import com.avmakarov.school.model.service.SubjectService;
import com.avmakarov.school.web.app.transport.SubjectTO;
import org.mapstruct.*;

import java.util.List;

@Mapper
public abstract class SubjectMapper {

    @Named("transportSubject")
    @Mapping(target = "oid", source = "id")
    @Mapping(target = "title")
    @Mapping(target = "comment")
    public abstract SubjectTO transportSubject(Subject domain);

    @IterableMapping(qualifiedByName = "transportSubject")
    public abstract List<SubjectTO> list(Iterable<Subject> all);

    public Subject domain(SubjectService service, SubjectTO transport) {
        Subject domain = null;
        if (transport.oid() != null) {
            domain = service.findOne(transport.oid());
        }
        if (domain == null) {
            domain = new Subject();
        }
        domain(transport, domain);
        return domain;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title")
    @Mapping(target = "comment")
    protected abstract void domain(SubjectTO transport, @MappingTarget Subject domain);

}
