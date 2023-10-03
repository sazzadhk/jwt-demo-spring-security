package com.shazzad.jwtspringsecuritydemo.repository;

import com.shazzad.jwtspringsecuritydemo.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
