package com.academic.ISSProject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date signDate;
   /* @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="student", nullable=false)*/
    private Long studentId;
    private String fisier;

}
