package com.academic.ISSProject.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long profileId;
}
