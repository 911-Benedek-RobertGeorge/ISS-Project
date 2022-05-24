package com.academic.ISSProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;
}
