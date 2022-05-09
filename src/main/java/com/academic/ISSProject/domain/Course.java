package com.academic.ISSProject.domain;

import com.academic.ISSProject.domain.enums.Required;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
