package com.academic.ISSProject.domain;


import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String role;

    /*@ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();*/

    public UserInfo(UserInfoDto userInfoDto) {
        firstName = userInfoDto.getFirstName();
        lastName = userInfoDto.getLastName();
        username = userInfoDto.getUsername();
        password = userInfoDto.getPassword();
        role = userInfoDto.getRole();
    }
}
