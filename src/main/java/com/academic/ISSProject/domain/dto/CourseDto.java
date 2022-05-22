package com.academic.ISSProject.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDto {

    private String courseName;
    private int credits;

}
