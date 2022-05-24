package com.academic.ISSProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private String sex;
    private String city;
    private  String address;
    private String mailAddress;
    private String phoneNumber;
    private Integer age;
}
