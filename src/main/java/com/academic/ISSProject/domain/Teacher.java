package com.academic.ISSProject.domain;

import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Data
@Table(name = "teacher")
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*@OneToOne(mappedBy="profile", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "id")*/
    private Long profileId;
   /* @OneToOne(mappedBy="user_info", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "id")*/
    private Long userInfoId;
    private Boolean chefDepartment;


}
