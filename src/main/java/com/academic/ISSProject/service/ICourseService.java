package com.academic.ISSProject.service;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.dto.CourseDto;

import java.util.List;

public interface ICourseService {

    List<Course> findAll();

    Course findById(long id);

    Course save(CourseDto courseInfo);

    void deleteById(long id);
}
