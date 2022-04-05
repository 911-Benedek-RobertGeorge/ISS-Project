package com.academic.ISSProject.domain;

import com.academic.ISSProject.domain.enums.Required;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "course")

public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    private int credits;
    private int maximumStudents;
    private long teacherId;
    private long curriculumId;
    private int followers;
    private Required required;

}
