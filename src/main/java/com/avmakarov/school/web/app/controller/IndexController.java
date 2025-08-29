package com.avmakarov.school.web.app.controller;

import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/app/index")
public class IndexController {

    private final UserService userService;
    private final UserController userController;

    public IndexController(UserService userService, UserController userController) {
        this.userService = userService;
        this.userController = userController;
    }

    @Transactional(readOnly = true)
    @GetMapping("/currentUser")
    public Map<String, Object> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByLogin(authentication.getName());
        if (user.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Object> model = new HashMap<>();
        model.put("hasStudents", !user.get().getStudents().isEmpty());
        model.put("isTeacher", user.get().getTeacher() != null);
        model.put("canEditClasses", user.get().isAdmin());
        model.put("canEditTeachers", user.get().isAdmin());
        model.put("canEditSubjects", user.get().isAdmin());
        model.put("canEditUsers", user.get().isAdmin());
        return model;
    }
}
