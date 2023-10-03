package com.shazzad.jwtspringsecuritydemo.service;

import com.shazzad.jwtspringsecuritydemo.constant.ExceptionConstant;
import com.shazzad.jwtspringsecuritydemo.exception.ResourceNotFoundException;
import com.shazzad.jwtspringsecuritydemo.model.entity.RefreshToken;
import com.shazzad.jwtspringsecuritydemo.model.entity.User;
import com.shazzad.jwtspringsecuritydemo.repository.RefreshTokenRepository;
import com.shazzad.jwtspringsecuritydemo.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl {
    private final RefreshTokenRepository repository;
    private final UserRepository userRepository;
    public RefreshToken generateRefreshToken(String username){
        User user = userRepository.getUserByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstant.USER_NOT_FOUND));
        RefreshToken refreshToken = RefreshToken.builder()
            .token(UUID.randomUUID())
            .expiry(LocalDateTime.now().plusHours(24))
            .user(user)
            .build();

        repository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(UUID token){
        RefreshToken refreshToken = repository.findById(token)
            .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstant.REFRESH_TOKEN_NOT_FOUND));

        if(refreshToken.getExpiry().isBefore(LocalDateTime.now())){
            repository.delete(refreshToken);
            return generateRefreshToken(refreshToken.getUser().getUsername());
        }else{
            return refreshToken;
        }
    }
}
