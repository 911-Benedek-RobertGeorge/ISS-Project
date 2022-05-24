package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.service.implementation.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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