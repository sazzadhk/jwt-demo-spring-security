package com.shazzad.jwtspringsecuritydemo.controller;

import static com.shazzad.jwtspringsecuritydemo.constant.ApiEndPointConstant.USER_API_ENDPOINT;

import com.shazzad.jwtspringsecuritydemo.model.dto.request.AuthenticationRequest;
import com.shazzad.jwtspringsecuritydemo.model.dto.request.UserCreateRequest;
import com.shazzad.jwtspringsecuritydemo.model.dto.response.AuthenticationResponse;
import com.shazzad.jwtspringsecuritydemo.model.dto.response.UserResponse;
import com.shazzad.jwtspringsecuritydemo.service.UserServiceImpl;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(USER_API_ENDPOINT)
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public void saveUser(UserCreateRequest request){
        userService.saveUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public AuthenticationResponse login(AuthenticationRequest request){
        return userService.authenticate(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/refresh-token")
    public AuthenticationResponse refreshAccessToken(UUID refreshToken){
        return userService.getAccessTokenByRefreshToken(refreshToken);
    }
}
