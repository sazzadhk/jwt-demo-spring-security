package com.shazzad.jwtspringsecuritydemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Department {
    SOFTWARE_DEPARTMENT("Software Department"),
    HUMAN_RESOURCE_DEPARTMENT("Human Resource Department"),
    ACCOUNTING_DEPARTMENT("Accounting Department");

    private final String departmentDesc;
}
