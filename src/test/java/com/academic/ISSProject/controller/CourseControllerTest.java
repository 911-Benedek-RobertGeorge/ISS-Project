package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.dto.CourseDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.CourseService;
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
class CourseControllerTest {

    @Mock
    private CourseService courseService;
    @InjectMocks
    private CourseController testController;

    @Test
    void getAll() {
        List<Course> list = new ArrayList<>();
        Course course = new Course();
        course.setId(1L);

        Course course1 = new Course();
        course1.setId(2L);

        list.add(course);
        list.add(course1);

        when(courseService.findAll()).thenReturn(list);
        assertEquals(testController.getAll(),list);
    }

    @Test
    void getCourse() {
        Course course = new Course();
        course.setId(1L);

        when(courseService.findById(1L)).thenReturn(course);
        assertEquals(testController.getCourse(1L), course);
    }

    @Test
    void addCourse() {
        Course course = new Course();
        course.setId(1L);

        when(courseService.save(any(CourseDto.class))).thenReturn(course);
        assertEquals(testController.addCourse(new CourseDto()), course);
    }

    @Test
    void deleteCourse() {
    }
}