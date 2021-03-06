package com.academic.ISSProject.domain;

import com.academic.ISSProject.domain.enums.Required;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;



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
    private int followers;

    public Course(String courseName, int credits, int maximumStudents, long teacherId, int followers, Required required, Curriculum curriculum) {
        this.courseName = courseName;
        this.credits = credits;
        this.maximumStudents = maximumStudents;
        this.teacherId = teacherId;
        this.followers = followers;
        this.required = required;
        this.curriculum = curriculum;
    }

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('OPTIONAL','MANDATORY')")
    private Required required;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
     private List<Grade> grades;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="curriculum_id")
    private Curriculum curriculum;

}
