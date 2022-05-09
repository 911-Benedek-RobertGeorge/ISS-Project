package com.academic.ISSProject.domain;

import com.academic.ISSProject.domain.dto.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sex;
    private String city;
    private  String address;
    private String mailAddress;
    private String phoneNumber;
    private Integer age;

    public Profile(ProfileDto profileDto) {
        sex= profileDto.getSex();
        city = profileDto.getCity();
        address = profileDto.getAddress();
        mailAddress = profileDto.getMailAddress();
        phoneNumber = profileDto.getPhoneNumber();
        age = profileDto.getAge();

    }
}
