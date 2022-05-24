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

    /**
     * Endpoint that receives all Courses
     * @return List<Course>
     */
    @GetMapping
    public List<Course> getAll(){
        return this.courseService.findAll();
    }

    /**
     * Endpoint that receives a specific Course
     * @param courseId course's id
     * @return Course object
     */
    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable long courseId){
        Course course = courseService.findById(courseId);
        if (course == null){
            throw new NoSuchElementException("The course with id " + courseId + " was not found.\n");
        }
        return course;
    }

    /**
     * Endpoint that adds a new course
     * @param courseDto course's information
     * @return added Course
     */
    @PostMapping
    public Course addCourse(@RequestBody CourseDto courseDto){
        return courseService.save(courseDto);
    }

    /**
     * Endpoint that deletes a specific course
     * @param courseId course's id
     * @return deleted course
     */
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
