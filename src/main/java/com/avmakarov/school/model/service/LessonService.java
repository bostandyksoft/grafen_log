package com.avmakarov.school.model.service;

import com.avmakarov.school.exceptions.NotFoundException;
import com.avmakarov.school.model.domain.Lesson;
import com.avmakarov.school.model.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson findOne(Long oid) {
        return lessonRepository.findById(oid).orElseThrow(()->new NotFoundException.Lesson(oid));
    }

    public Lesson save(Lesson domain) {
        return lessonRepository.save(domain);
    }
}
