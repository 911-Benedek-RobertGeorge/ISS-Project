package com.academic.ISSProject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studentId;
    private Long courseId;
    private int grade;
    @DateTimeFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date receivedDate;


}
