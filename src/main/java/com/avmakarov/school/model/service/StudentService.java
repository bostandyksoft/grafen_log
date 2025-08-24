package com.avmakarov.school.model.service;

import com.avmakarov.school.exceptions.NotFoundException;
import com.avmakarov.school.model.domain.Student;
import com.avmakarov.school.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findById(oid).orElseThrow(()->new NotFoundException.Student(oid));
    }

    public Student save(Student domain) {
        return repository.save(domain);
    }
}
