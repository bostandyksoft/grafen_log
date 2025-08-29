package com.avmakarov.school.runner;

import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DefaultAdminRunner implements ApplicationRunner {

    @Value("${log.admin.password:admin}")
    private String adminPassword;

    private final UserService userService;

    @Autowired
    public DefaultAdminRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userService.noAdmins()) {
            User admin = userService.register("admin", adminPassword, "Администратор");
            admin.setAdmin(true);
            admin.setActive(true);
            userService.save(admin);
        }
    }
}
