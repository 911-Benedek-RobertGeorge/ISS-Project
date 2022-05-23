package com.academic.ISSProject.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer yearsOfStudy;

    @JsonIgnore
    @OneToMany(mappedBy = "specialization")
    private List<Curriculum> curriculums;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "enroll",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;
}
