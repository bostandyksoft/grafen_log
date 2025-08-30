package com.avmakarov.school.web.app.controller;

import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/index")
public class IndexController {

    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @GetMapping("/currentUser")
    public Map<String, Object> currentUser() {
        User user = userService.getCurrentUser();
        Map<String, Object> model = new HashMap<>();
        model.put("hasStudents", !user.getStudents().isEmpty());
        model.put("isTeacher", user.getTeacher() != null);
        model.put("canEditClasses", user.isAdmin());
        model.put("canEditTeachers", user.isAdmin());
        model.put("canEditSubjects", user.isAdmin());
        model.put("canEditUsers", user.isAdmin());
        return model;
    }
}
