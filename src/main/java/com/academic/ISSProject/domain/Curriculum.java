package com.academic.ISSProject.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "curriculum")

public class Curriculum {
    @Id
    @GeneratedValue
    private Long id;
    private int year;
    private String curriculumName;
    private String language;
}
