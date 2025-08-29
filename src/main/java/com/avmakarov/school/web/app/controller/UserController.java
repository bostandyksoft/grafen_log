package com.avmakarov.school.web.app.controller;

import com.avmakarov.school.model.service.UserService;
import com.avmakarov.school.web.app.mapper.UserMapper;
import com.avmakarov.school.web.app.transport.UserTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user")
public class UserController {

    private final UserMapper mapper;
    private final UserService service;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper mapper, UserService service, UserService userService) {
        this.mapper = mapper;
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public UserTO getUser(@RequestParam("id") Long id) {
        return mapper.transport(
                userService.findOne(id)
        );
    }

    @GetMapping("/all")
    public List<UserTO.Table> all() {
        return mapper.list(service.findAll());
    }

    @Transactional
    @PostMapping
    public  UserTO save(@Valid @RequestBody UserTO transport) {
        return mapper.transport(
                service.save(
                        mapper.domain(transport)
                )
        );
    }

    @Transactional
    @DeleteMapping
    public void delete(@RequestBody List<Long> ids) {
        service.deleteAll(ids);
    }
}
