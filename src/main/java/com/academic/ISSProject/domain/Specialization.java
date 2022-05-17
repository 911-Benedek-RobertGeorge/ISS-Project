package com.academic.ISSProject.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "specialization")
@AllArgsConstructor
@NoArgsConstructor
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer yearsOfStudy;

    @OneToMany(mappedBy = "specialization")
    private Set<Curriculum> curriculums;

    @ManyToMany
    @JoinTable(
            name = "enroll",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student> students;
}
