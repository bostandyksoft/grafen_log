package com.avmakarov.school.web.app.controller;

import com.avmakarov.school.model.service.TeacherService;
import com.avmakarov.school.web.app.mapper.TeacherMapper;
import com.avmakarov.school.web.app.transport.TeacherTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/teacher")
public class TeacherController {

    private final TeacherMapper mapper;
    private final TeacherService service;

    @Autowired
    public TeacherController(TeacherMapper mapper, TeacherService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/all")
    public List<TeacherTO> all() {
        return mapper.list(service.findAll());
    }

    @Transactional
    @PostMapping
    public TeacherTO save(@Valid @RequestBody TeacherTO transport) {
        return mapper.transportTeacher(
                service.save(
                        mapper.domain(service, transport)
                )
        );
    }

    @Transactional
    @DeleteMapping
    public void delete(@RequestBody List<Long> ids) {
        service.removeAll(ids);
    }
}
