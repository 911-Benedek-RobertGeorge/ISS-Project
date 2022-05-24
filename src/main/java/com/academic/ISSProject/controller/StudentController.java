package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Grade;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Endpoint that receives the list with all Students
     * @return List<Students>>
     */
    @GetMapping
    public List<Student> getAll(){
        return this.studentService.findAll();
    }

    /**
     * Endpoint that returns the student having the requested ID
     * @param studentId the id of the student
     * @return Student Object
     */
    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable long studentId){
        Student theStudent = studentService.findById(studentId);
        if(theStudent == null){
            throw new NoSuchElementException("The student with id " + studentId + " was not found.\n");
        }
        return theStudent;
    }

    /**
     * Post endpoint that adds a new student
     * @param userInfoDto user's data send
     * @return the saved Student
     */
    @PostMapping
    public Student addStudent(@RequestBody UserInfoDto userInfoDto){
        return studentService.save(userInfoDto);
    }


    /**
     * Endpoint that updates a user's profile
     * @param studentId the student's id
     * @param profileDto information about the updated profile
     * @param principal
     * @return the updated Student
     */
    @PutMapping("/{studentId}/profile")
    public Student updateProfile(@PathVariable Long studentId,@RequestBody ProfileDto profileDto ,Principal principal  ){

        String username = principal.getName();
        return studentService.updateProfile(studentId,profileDto,username);
    }

    /**
     * Endpoint that deletes a student
     * @param studentId the student's id
     * @return deleted student
     */
    @DeleteMapping("/{studentId}")
    public Student deleteStudent(@PathVariable long studentId) {
        Student theStudent = this.studentService.findById(studentId);

        if (theStudent == null) {
            throw new NoSuchElementException("The student with id " + studentId + " was not found.\n");
        }

        this.studentService.deleteById(studentId);
        return theStudent;
    }

    /**
     * Endpoint that return the grades for a specific Student
     * @param studentId the student's id
     * @param specId specialisation's id
     * @param year the year of the student
     * @return List<Grades>
     */
    @GetMapping("/{studentId}/specialization/{specId}/year/{year}/grades")
    public List<Grade> getGradesForStudent(@PathVariable Long studentId,
                                           @PathVariable Long specId,
                                           @PathVariable Long year){
        return  studentService.getGradesForStudent(studentId,specId,year);
    }


}



