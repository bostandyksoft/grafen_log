package com.avmakarov.school.web.app.admin.controller;

import com.avmakarov.school.model.service.ClassService;
import com.avmakarov.school.web.mapper.ClassMapper;
import com.avmakarov.school.web.transport.ClassTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/admin/class")
public class ClassController {

    private final ClassMapper classMapper;
    private final ClassService classService;

    @Autowired
    public ClassController(ClassMapper classMapper, ClassService classService) {
        this.classMapper = classMapper;
        this.classService = classService;
    }

    @GetMapping("/all")
    public List<ClassTO> all() {
        return classMapper.list(classService.findAll());
    }

    @Transactional
    @PostMapping("/save")
    public  ClassTO save(@Valid @RequestBody ClassTO classTO) {
        return classMapper.transportClass(
                classService.save(
                        classMapper.domain(classService, classTO)
                )
        );
    }
}
