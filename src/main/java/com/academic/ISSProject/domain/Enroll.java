package com.academic.ISSProject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "enroll")
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="specialization_id")
    private Specialization specialization;

}
