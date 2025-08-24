package com.avmakarov.school.web.mapper;

import com.avmakarov.school.model.domain.SchoolClass;
import com.avmakarov.school.model.service.ClassService;
import com.avmakarov.school.web.transport.ClassTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public abstract class ClassMapper {

    @Named("classTo")
    @Mapping(target = "oid", source = "id")
    @Mapping(target = "name")
    public abstract ClassTO transportClass(SchoolClass schoolClass);

    @IterableMapping(qualifiedByName = "classTo")
    public abstract List<ClassTO> list(Iterable<SchoolClass> all);

    public SchoolClass domain(ClassService classService, ClassTO classTO) {
        SchoolClass schoolClass = null;
        if (classTO.oid() != null) {
            schoolClass = classService.findOne(classTO.oid());
        }
        if (schoolClass == null) {
            schoolClass = new SchoolClass();
        }
        schoolClass.setName(classTO.name());
        return schoolClass;
    }
}
