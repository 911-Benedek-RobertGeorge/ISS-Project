package com.academic.ISSProject.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "curriculum")
@AllArgsConstructor
@NoArgsConstructor
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String curriculumName;
    private String language;


    //private String specialization;

    @OneToMany(mappedBy = "curriculum", fetch = FetchType.LAZY)
    private List<Contract> contracts;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="specialization_id")
    private Specialization specialization;
}

///TODO get curr by specialization

