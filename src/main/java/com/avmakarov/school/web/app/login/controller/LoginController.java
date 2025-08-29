package com.avmakarov.school.web.app.login.controller;

import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app/login")
public class LoginController {

    private final PasswordEncoder encoder;
    private final UserService userService;

    @Autowired
    public LoginController(PasswordEncoder encoder, UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/register")
    public void register(@RequestBody Map<String, String> registrationData) {
        userService.register(
                new User(
                        registrationData.get("username"),
                        encoder.encode(registrationData.get("pass")),
                        registrationData.get("info")
                )
        );
    }

}
