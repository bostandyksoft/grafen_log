package com.avmakarov.school.model.service;

import com.avmakarov.school.exceptions.NotFoundException;
import com.avmakarov.school.model.domain.SchoolClass;
import com.avmakarov.school.model.domain.Student;
import com.avmakarov.school.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public Iterable<Student> findAll() {
        return repository.findAll();
    }

    public Student findOne(Long oid) {
        return repository.findById(oid).orElseThrow(() -> new NotFoundException.Student(oid));
    }

    public Student save(Student domain) {
        return repository.save(domain);
    }

    public List<Student> findAllByIds(List<Long> ids) {
        return StreamSupport.stream(
                repository.findAllById(ids).spliterator(), false
        ).collect(Collectors.toList());
    }

    public void removeAll(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    public List<Student> findAllByClass(SchoolClass schoolClass) {
        return repository.findAllBySchoolClass(schoolClass);
    }
}
