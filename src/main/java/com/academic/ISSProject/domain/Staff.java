package com.academic.ISSProject.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long profileId;
}
