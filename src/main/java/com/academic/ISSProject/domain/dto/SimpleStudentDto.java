package com.academic.ISSProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleStudentDto {
    private Long id;
    private String firstName;
    private String lastName;
}
