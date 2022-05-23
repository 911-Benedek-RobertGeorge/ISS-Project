package com.academic.ISSProject.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Contract> contracts;

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @JsonIgnore
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Specialization> specializations;
}
