package com.academic.ISSProject.domain.dto;

import com.academic.ISSProject.domain.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationDto {
    Specialization specialization;
    Integer nextYear;

}
