package com.academic.ISSProject.domain;

import com.academic.ISSProject.domain.enums.Required;
import lombok.Data;

import javax.persistence.*;


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
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('OPTIONAL','MANDATORY')")
    private Required required;


}
