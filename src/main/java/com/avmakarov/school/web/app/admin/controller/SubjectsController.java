package com.avmakarov.school.web.app.admin.controller;

import com.avmakarov.school.model.service.SubjectService;
import com.avmakarov.school.web.app.admin.mapper.SubjectMapper;
import com.avmakarov.school.web.app.admin.transport.SubjectTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/admin/subject")
public class SubjectsController {

    private final SubjectMapper mapper;
    private final SubjectService service;

    @Autowired
    public SubjectsController(SubjectService service, SubjectMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/all")
    public List<SubjectTO> all() {
        return mapper.list(service.findAll());
    }

    @Transactional
    @PostMapping
    public SubjectTO save(@Valid @RequestBody SubjectTO transport) {
        return mapper.transportSubject(
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
