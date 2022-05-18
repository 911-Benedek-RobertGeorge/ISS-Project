package com.academic.ISSProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumDto {
    private Long id;
    private int year;
    private String curriculumName;
    private String language;
}
