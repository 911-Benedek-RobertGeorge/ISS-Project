package com.academic.ISSProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
