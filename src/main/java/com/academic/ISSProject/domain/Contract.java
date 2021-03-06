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
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date signDate;


    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

     @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="curriculum_id")
    private Curriculum curriculum;

    private String fisier;

}
