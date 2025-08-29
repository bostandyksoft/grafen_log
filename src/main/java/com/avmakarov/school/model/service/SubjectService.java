package com.avmakarov.school.model.service;

import com.avmakarov.school.exceptions.NotFoundException;
import com.avmakarov.school.model.domain.Subject;
import com.avmakarov.school.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository repository;

    @Autowired
    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public Iterable<Subject> findAll() {
        return repository.findAll();
    }

    public Subject findOne(Long oid) {
        return repository.findById(oid).orElseThrow(()->new NotFoundException.Subject(oid));
    }

    public Subject save(Subject domain) {
        return repository.save(domain);
    }

    public void removeAll(List<Long> ids) {
        repository.deleteAllById(ids);
    }
}
