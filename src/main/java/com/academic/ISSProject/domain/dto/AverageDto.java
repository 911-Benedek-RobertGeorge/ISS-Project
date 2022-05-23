package com.academic.ISSProject.domain.dto;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AverageDto {
    private String name;
    private Double average;
}
