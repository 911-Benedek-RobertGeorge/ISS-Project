package com.academic.ISSProject.domain;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long profileId;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Contract> contracts;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Specialization> specializations;
}
