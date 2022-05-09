package com.academic.ISSProject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date receivedDate;


}
