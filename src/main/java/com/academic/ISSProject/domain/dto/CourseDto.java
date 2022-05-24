package com.academic.ISSProject.domain.dto;


import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.enums.Required;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private String courseName;
    private int credits;
    private int maximumStudents;
    private long teacherId;
    private int followers;
    //private Required required;
    private long curriculum_id;

}
