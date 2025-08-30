package com.avmakarov.school.model.repository;

import com.avmakarov.school.model.domain.SchoolClass;
import com.avmakarov.school.model.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAllBySchoolClass(SchoolClass schoolClass);
}