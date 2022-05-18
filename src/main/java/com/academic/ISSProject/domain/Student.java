package com.academic.ISSProject.domain;


import lombok.Data;


import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Contract> contracts;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Grade> grades;

 
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Specialization> specializations;
}
