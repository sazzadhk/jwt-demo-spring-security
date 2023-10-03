package com.shazzad.jwtspringsecuritydemo.repository;

import com.shazzad.jwtspringsecuritydemo.model.entity.RefreshToken;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}
