package com.avmakarov.school.web.app.controller;

import com.avmakarov.school.model.service.ClassService;
import com.avmakarov.school.web.app.mapper.ClassMapper;
import com.avmakarov.school.web.app.transport.ClassTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/class")
public class ClassController {

    private final ClassMapper classMapper;
    private final ClassService classService;

    @Autowired
    public ClassController(ClassMapper classMapper, ClassService classService) {
        this.classMapper = classMapper;
        this.classService = classService;
    }

    @GetMapping("/all")
    public List<ClassTO.Table> all() {
        return classMapper.list(classService.findAll());
    }

    @Transactional
    @GetMapping
    public ClassTO load(@RequestParam("id") Long id) {
        return classMapper.transportClass(
                classService.findOne(id)
        );
    }

    @Transactional
    @PostMapping
    public  ClassTO save(@Valid @RequestBody ClassTO classTO) {
        return classMapper.transportClass(
                classService.save(
                        classMapper.domain(classTO)
                )
        );
    }

    @Transactional
    @DeleteMapping
    public void delete(@RequestBody List<Long> ids) {
        classService.removeAll(ids);
    }
}
