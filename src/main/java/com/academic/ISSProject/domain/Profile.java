package com.academic.ISSProject.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue
    private Long id;
    private String sex;
    private String city;
    private  String address;
    private String mailAddress;
    private String phoneNumber;
    private Integer age;
}
