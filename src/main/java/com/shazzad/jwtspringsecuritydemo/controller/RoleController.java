package com.shazzad.jwtspringsecuritydemo.controller;

import static com.shazzad.jwtspringsecuritydemo.constant.ApiEndPointConstant.ROLE_API_ENDPOINT;

import com.shazzad.jwtspringsecuritydemo.model.dto.request.RoleCreateRequest;
import com.shazzad.jwtspringsecuritydemo.model.dto.response.RoleResponse;
import com.shazzad.jwtspringsecuritydemo.service.RoleServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ROLE_API_ENDPOINT)
@RequiredArgsConstructor
public class RoleController {
    private final RoleServiceImpl roleService;

    @PostMapping
    public void saveRole(RoleCreateRequest request){
        roleService.saveRole(request);
    }

    @GetMapping
    public List<RoleResponse> getAllRoles(){
        return roleService.getAll();
    }
}
