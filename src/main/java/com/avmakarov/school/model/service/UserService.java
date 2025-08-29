package com.avmakarov.school.model.service;

import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByLogin(String username) {
        return userRepository.findByLoginIgnoreCase(username);
    }

    public void register(User user) {
        userRepository.save(user);
    }
}
