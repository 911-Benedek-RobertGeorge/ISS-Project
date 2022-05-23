package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.dto.CourseDto;
import com.academic.ISSProject.service.implementation.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAll(){
        return this.courseService.findAll();
    }

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable long courseId){
        Course course = courseService.findById(courseId);
        if (course == null){
            throw new NoSuchElementException("The course with id " + courseId + " was not found.\n");
        }
        return course;
    }

    @PostMapping
    public Course addCourse(@RequestBody CourseDto courseDto){
        return courseService.save(courseDto);
    }

    @DeleteMapping("/{courseId}")
    public Course deleteCourse(@PathVariable long courseId){
        Course course = this.courseService.findById(courseId);

        if(course == null){
            throw new NoSuchElementException("The course with id " + courseId + " was not found.\n");
        }

        this.courseService.deleteById(courseId);
        return course;
    }
}
