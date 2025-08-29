package com.avmakarov.school.model.repository;

import com.avmakarov.school.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLoginIgnoreCase(String username);

    int countUserByAdmin(boolean admin);
}
