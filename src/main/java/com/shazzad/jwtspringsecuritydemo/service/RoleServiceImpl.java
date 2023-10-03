package com.shazzad.jwtspringsecuritydemo.service;

import com.shazzad.jwtspringsecuritydemo.model.dto.request.RoleCreateRequest;
import com.shazzad.jwtspringsecuritydemo.model.dto.response.RoleResponse;
import com.shazzad.jwtspringsecuritydemo.model.entity.Role;
import com.shazzad.jwtspringsecuritydemo.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final RoleRepository roleRepository;
    public void saveRole(RoleCreateRequest request){
        Role role = new Role();
        role.setRoleName(request.roleName());
        roleRepository.save(role);
    }

    public List<RoleResponse> getAll(){
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
            .map(role -> new RoleResponse(role.getId(), role.getRoleName()))
            .toList();
    }

    public List<Role> getAllRoleByIds(List<Long> roleIds){
       return roleRepository.findAllById(roleIds);
    }
}
