package com.academic.ISSProject.domain;

import com.academic.ISSProject.domain.enums.Required;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


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
    private Long teacherId;
    private int followers;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('OPTIONAL','MANDATORY')")
    private Required required;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
     private Set<Grade> grades;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="curriculum_id")
    private Curriculum curriculum;

}
