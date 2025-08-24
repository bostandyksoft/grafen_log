package com.avmakarov.school.model.service;

import com.avmakarov.school.exceptions.NotFoundException;
import com.avmakarov.school.model.domain.SchoolClass;
import com.avmakarov.school.model.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Iterable<SchoolClass> findAll() {
        return classRepository.findAll();
    }

    public SchoolClass findOne(Long id) {
        return classRepository.findById(id).orElseThrow(()->new NotFoundException.Class(id));
    }

    public SchoolClass save(SchoolClass donain) {
        return classRepository.save(donain);
    }
}
