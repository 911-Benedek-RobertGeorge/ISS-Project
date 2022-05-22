package com.academic.ISSProject.domain.dto;

import com.academic.ISSProject.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentGradeDto {
    Student student;
    Double average;
}
