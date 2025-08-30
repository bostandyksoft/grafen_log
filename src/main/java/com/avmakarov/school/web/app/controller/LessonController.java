package com.avmakarov.school.web.app.controller;

import com.avmakarov.school.model.service.LessonService;
import com.avmakarov.school.web.app.mapper.LessonMapper;
import com.avmakarov.school.web.app.transport.LessonTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/lesson")
public class LessonController {

    private final LessonMapper mapper;
    private final LessonService service;

    @Autowired
    public LessonController(LessonMapper mapper, LessonService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Transactional
    @PostMapping
    public LessonTO save(@Valid @RequestBody LessonTO transport) {
        return mapper.transport(
                service.save(
                        mapper.domain(transport)
                )
        );
    }
}
