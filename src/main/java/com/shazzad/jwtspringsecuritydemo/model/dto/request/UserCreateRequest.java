package com.shazzad.jwtspringsecuritydemo.model.dto.request;

import com.shazzad.jwtspringsecuritydemo.model.enums.Department;
import java.util.List;

public record UserCreateRequest(
    String username,
    String password,
    Department department,
    List<Long> roleIds
) {
}
