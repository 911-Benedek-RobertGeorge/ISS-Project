package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.*;
import com.academic.ISSProject.domain.dto.CourseDto;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    /**
     * Function that returns all teachers
     * @return List<Teacher>
     */
    @GetMapping
    public List<Teacher> getAll(){
        return this.teacherService.findAll();
    }

    /**
     * Endpoint that return the teacher having the requested ID
     * @param teacherId the id of the teacher
     * @return Teacher object
     */
    @GetMapping("/{teacherId}")
    public Teacher getTeacher(@PathVariable long teacherId){
        Teacher teacher = teacherService.findById(teacherId);
        if (teacher == null){
            throw new NoSuchElementException("The teacher with id " + teacherId + " was not found.\n");
        }
        return teacher;
    }

    /**
     * Endpoint that adds a new Teacher
     * @param userInfoDto teacher's data
     * @return the saved Teacher
     */
    @PostMapping
    public Teacher addTeacher(@RequestBody UserInfoDto userInfoDto){
        return teacherService.save(userInfoDto);
    }

    /**
     * Endpoint that updates a teacher's profile
     * @param teacherId the student's id
     * @param profileDto teacher data to be updated
     * @return the updated Teacher
     */
    @PutMapping("/{teacherId}/profile")
    public Teacher updateProfile(@PathVariable Long teacherId, @RequestBody ProfileDto profileDto, Principal principal){
        String username = principal.getName();

        return teacherService.updateProfile(teacherId, profileDto, username);
    }

    /**
     * Endpoint that deletes a teacher
     * @param teacherId the teacher's id
     * @return deleted Teacher
     */
    @DeleteMapping("/{teacherId}")
    public Teacher deleteTeacher(@PathVariable long teacherId){
        Teacher teacher = this.teacherService.findById(teacherId);

        if(teacher == null){
            throw new NoSuchElementException("The teacher with id " + teacherId + " was not found.\n");
        }

        this.teacherService.deleteById(teacherId);
        return teacher;
    }

    /**
     * Endpoint that adds a new grade as a teacher
     * @param teacherId teacher's id that posts the grade
     * @param studentId student's id that receive the grade
     * @param courseId course's id that the grade is assigned to
     * @param grade the value of the grade
     * @return the added grade
     */
    @PostMapping("/{teacherId}/student/{studentId}/course/{courseId}/grade/{grade}")
    public Grade postGrade(@PathVariable Long teacherId,
                           @PathVariable Long studentId,
                           @PathVariable Long courseId,
                           @PathVariable Integer grade){
        return teacherService.postGrade(teacherId,studentId,courseId,grade);
    }

    /**
     * Endpoint that propose a new optional course as a teacher
     * @param teacherId teacher's id that propose the course
     * @param curriculumId curriculum's id that the course belongs to
     * @param courseDto data about the course to be added
     * @return the optional course object
     */
    @PostMapping("/{teacherId}/curriculum/{curriculumId}/optional")
    public Course proposeOptionalCourse(@PathVariable Long teacherId,
                                        @PathVariable Long curriculumId,
                                        @RequestBody CourseDto courseDto){
        return teacherService.proposeOptionalCourse(teacherId,curriculumId,courseDto);
    }



}
