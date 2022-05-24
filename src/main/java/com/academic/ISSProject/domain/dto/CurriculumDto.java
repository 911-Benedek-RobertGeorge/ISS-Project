package com.academic.ISSProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurriculumDto {
    private Long id;
    private Integer year;
    private String curriculumName;
    private String language;
    private Long specializationId;
}
