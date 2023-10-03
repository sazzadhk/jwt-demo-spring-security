package com.shazzad.jwtspringsecuritydemo.repository;

import com.shazzad.jwtspringsecuritydemo.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserByUsername(String username);
}
