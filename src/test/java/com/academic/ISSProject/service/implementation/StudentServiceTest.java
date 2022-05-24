package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.Profile;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.UserInfo;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.repository.ProfileRepository;
import com.academic.ISSProject.repository.StudentRepository;
import com.academic.ISSProject.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private   UserInfoRepository userInfoRepository;
    @Mock
    private   ProfileRepository profileRepository;
    @Mock
    private   PasswordEncoder passwordEncoder;
    @InjectMocks
    private StudentService testService;

    @Test
    void findAll() {
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setId(1L);
        Student student1 = new Student();
        student1.setId(2L);
        student.setId(1L);
        list.add(student);
        list.add(student1);
        when(studentRepository.findAll()).thenReturn(list);
        assertEquals(testService.findAll(),list);
    }

    @Test
    void findById() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        assertEquals(testService.findById(1L),student);
    }

    @Test
    void updateProfile() {

        Profile profile = new Profile();
        profile.setCity("Cluj");
        Student student = new Student();
        student.setId(1L);
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("user");
        student.setUserInfo(userInfo);
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);
        when(studentRepository.getById(1L)).thenReturn(student);
        student.setProfile(profile);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        assertEquals(testService.updateProfile(1L,new ProfileDto(),"user"),student);
    }

    @Test
    void getGradesForStudent() {
    }
}