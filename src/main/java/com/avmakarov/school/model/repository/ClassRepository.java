package com.avmakarov.school.model.repository;

import com.avmakarov.school.model.domain.SchoolClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<SchoolClass, Long> {
}
