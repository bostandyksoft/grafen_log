package com.avmakarov.school.model.service;

import com.avmakarov.school.exceptions.NotFoundException;
import com.avmakarov.school.model.domain.User;
import com.avmakarov.school.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(@Lazy PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public Optional<User> findByLogin(String username) {
        return userRepository.findByLoginIgnoreCase(username);
    }

    public User register(String username, String password, String info) {
        return userRepository.save(new User(username, encoder.encode(password), info));
    }

    public boolean noAdmins() {
        return userRepository.countUserByAdmin(true) == 0;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(Long oid) {
        return userRepository.findById(oid).orElseThrow(()->new NotFoundException.User(oid));
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteAll(List<Long> ids) {
        userRepository.deleteAllById(ids);
    }
}
