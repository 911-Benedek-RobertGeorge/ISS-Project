package com.academic.ISSProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSpecializationDto {
    private Long id;
    private String name;
    private Integer yearsOfStudy;
}
