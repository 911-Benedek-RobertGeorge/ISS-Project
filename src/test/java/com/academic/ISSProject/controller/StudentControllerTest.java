package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class StudentControllerTest {

    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentController testController;

    @Test
    void getAll() {
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setId(1L);
        Student student1 = new Student();
        student1.setId(2L);
        student.setId(1L);
        list.add(student);
        list.add(student1);
        when(studentService.findAll()).thenReturn(list);
        assertEquals(testController.getAll(),list);
    }

    @Test
    void getStudent() {
        Student student = new Student();
        student.setId(1L);
        when(studentService.findById(1L)).thenReturn(student);
        assertEquals(testController.getStudent(1L),student);

    }

    @Test
    void addStudent() {
        Student student = new Student();
        student.setId(1L);
        when(studentService.save(any(UserInfoDto.class))).thenReturn(student);
        assertEquals(testController.addStudent(new UserInfoDto()),student);


    }

    @Test
    void getCurrentUser() {
    }

    @Test
    void updateProfile() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getGradesForStudent() {
    }
}