package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.TeacherService;
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
class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;
    @InjectMocks
    private TeacherController testController;

    @Test
    void getAll() {
        List<Teacher> list = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        Teacher teacher1 = new Teacher();
        teacher1.setId(2L);

        list.add(teacher);
        list.add(teacher1);

        when(teacherService.findAll()).thenReturn(list);
        assertEquals(testController.getAll(),list);
    }

    @Test
    void getTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        when(teacherService.findById(1L)).thenReturn(teacher);
        assertEquals(testController.getTeacher(1L), teacher);
    }

    @Test
    void addTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        when(teacherService.save(any(UserInfoDto.class))).thenReturn(teacher);
        assertEquals(testController.addTeacher(new UserInfoDto()), teacher);
    }

    @Test
    void updateProfile() {
    }

    @Test
    void deleteTeacher() {
    }

    @Test
    void postGrade() {
    }

    @Test
    void proposeOptionalCourse() {
    }
}