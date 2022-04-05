package com.academic.ISSProject.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "grade")

public class Grade {
    @Id
    @GeneratedValue

    private Long id;
    private Long studentId;
    private Long courseId;
    private int grade;
    private Date receivedDate;
}
