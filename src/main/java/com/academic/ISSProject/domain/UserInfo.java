package com.academic.ISSProject.domain;


import com.academic.ISSProject.domain.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

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
    private String password;

    public UserInfo(UserInfoDto userInfoDto) {
        firstName = userInfoDto.getFirstName();
        lastName = userInfoDto.getLastName();
        username = userInfoDto.getUsername();
        password = userInfoDto.getPassword();
    }
}
