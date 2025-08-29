package com.avmakarov.school.model.service;

import com.avmakarov.school.exceptions.NotFoundException;
import com.avmakarov.school.model.domain.Teacher;
import com.avmakarov.school.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.repository = teacherRepository;
    }

    public Iterable<Teacher> findAll() {
        return repository.findAll();
    }

    public Teacher findOne(Long oid) {
        return repository.findById(oid).orElseThrow(()->new NotFoundException.Teacher(oid));
    }

    public Teacher save(Teacher domain) {
        return repository.save(domain);
    }

    public void removeAll(List<Long> ids) {
        repository.deleteAllById(ids);
    }
}
