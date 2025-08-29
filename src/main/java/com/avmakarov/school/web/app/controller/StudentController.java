package com.avmakarov.school.web.app.controller;

import com.avmakarov.school.model.service.StudentService;
import com.avmakarov.school.web.app.mapper.StudentMapper;
import com.avmakarov.school.web.app.transport.StudentTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/student")
public class StudentController {

    private final StudentMapper mapper;
    private final StudentService service;

    @Autowired
    public StudentController(StudentMapper mapper, StudentService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/all")
    public List<StudentTO> all() {
        return mapper.transportStudents(service.findAll());
    }

    @Transactional
    @PostMapping
    public StudentTO save(@Valid @RequestBody StudentTO transport) {
        return mapper.transportStudent(
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
