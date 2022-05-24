package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.*;
import com.academic.ISSProject.domain.dto.*;
import com.academic.ISSProject.service.implementation.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAll(){
        return this.teacherService.findAll();
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeacher(@PathVariable long teacherId){
        Teacher teacher = teacherService.findById(teacherId);
        if (teacher == null){
            throw new NoSuchElementException("The teacher with id " + teacherId + " was not found.\n");
        }
        return teacher;
    }

    @PostMapping
    public Teacher addTeacher(@RequestBody UserInfoDto userInfoDto){
        return teacherService.save(userInfoDto);
    }

    @PutMapping("/{teacherId}/profile")
    public Teacher updateProfile(@PathVariable Long teacherId, @RequestBody ProfileDto profileDto){
        return teacherService.updateProfile(teacherId, profileDto);
    }

    @DeleteMapping("/{teacherId}")
    public Teacher deleteTeacher(@PathVariable long teacherId){
        Teacher teacher = this.teacherService.findById(teacherId);

        if(teacher == null){
            throw new NoSuchElementException("The teacher with id " + teacherId + " was not found.\n");
        }

        this.teacherService.deleteById(teacherId);
        return teacher;
    }

    @PostMapping("/{teacherId}/student/{studentId}/course/{courseId}/grade/{grade}")
    public Grade postGrade(@PathVariable Long teacherId,
                           @PathVariable Long studentId,
                           @PathVariable Long courseId,
                           @PathVariable Integer grade){
        return teacherService.postGrade(teacherId,studentId,courseId,grade);
    }

    @PostMapping("/{teacherId}/curriculum/{curriculumId}/optional")
    public Course proposeOptionalCourse(@PathVariable Long teacherId,
                                        @PathVariable Long curriculumId,
                                        @RequestBody CourseDto courseDto){
        return teacherService.proposeOptionalCourse(teacherId,curriculumId,courseDto);
    }

    @GetMapping("/{teacherId}/courses")
    public List<Course2Dto> getTeacherCourses(@PathVariable Long teacherId){
        List<Course> courses = teacherService.getCourses(teacherId);
        List<Course2Dto> courseDtoList = new ArrayList<>();

        courses.forEach((value) -> {
            courseDtoList.add(new Course2Dto(value.getId(), value.getCourseName()));
        });

        return courseDtoList;
    }

    @GetMapping("/{courseId}/course")
    public List<GradeDto> getGradesOfCourse(@PathVariable Long courseId){
        return teacherService.getGradesOfCourse(courseId);
    }

}
