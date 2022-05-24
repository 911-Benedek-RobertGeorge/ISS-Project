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
    private int grade;

    private Date receivedDate;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course course;





}
