package com.shazzad.jwtspringsecuritydemo.service;

import com.shazzad.jwtspringsecuritydemo.constant.ExceptionConstant;
import com.shazzad.jwtspringsecuritydemo.exception.ResourceNotFoundException;
import com.shazzad.jwtspringsecuritydemo.model.dto.request.AuthenticationRequest;
import com.shazzad.jwtspringsecuritydemo.model.dto.request.UserCreateRequest;
import com.shazzad.jwtspringsecuritydemo.model.dto.response.AuthenticationResponse;
import com.shazzad.jwtspringsecuritydemo.model.dto.response.RoleResponse;
import com.shazzad.jwtspringsecuritydemo.model.dto.response.UserResponse;
import com.shazzad.jwtspringsecuritydemo.model.entity.RefreshToken;
import com.shazzad.jwtspringsecuritydemo.model.entity.User;
import com.shazzad.jwtspringsecuritydemo.repository.UserRepository;
import java.sql.Ref;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenServiceImpl refreshTokenService;
    public void saveUser(UserCreateRequest request){
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setDepartment(request.department());
        user.setRoles(roleService.getAllRoleByIds(request.roleIds()));
        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream()
            .map(
                user -> {
                    List<RoleResponse> roleResponseList = user.getRoles().stream()
                        .map(role -> new RoleResponse(role.getId(), role.getRoleName()))
                        .toList();
                    return new UserResponse(user.getUsername(),user.getDepartment().getDepartmentDesc(),roleResponseList);
                }
            )
            .toList();
    }

    public Optional<User> getUser(String username){
        return userRepository.getUserByUsername(username);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
            )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        return AuthenticationResponse.builder()
            .username(request.username())
            .accessToken(jwtService.generateToken(userDetails))
            .refreshToken(refreshTokenService.generateRefreshToken(request.username()).getToken())
            .build();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstant.USER_NOT_FOUND));

        return new UserResponse(
            user.getUsername(),
            user.getDepartment().getDepartmentDesc(),
            user.getRoles().stream()
                .map(role -> new RoleResponse(role.getId(), role.getRoleName()))
                .toList()
        );
    }

    public AuthenticationResponse getAccessTokenByRefreshToken(UUID token){
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(token);
        return AuthenticationResponse.builder()
            .username(refreshToken.getUser().getUsername())
            .accessToken(jwtService.generateToken(refreshToken.getUser()))
            .refreshToken(refreshToken.getToken())
            .build();
    }
}
