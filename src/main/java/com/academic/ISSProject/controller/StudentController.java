package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.StudentService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll(){
        return this.studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable long studentId){
        Student theStudent = studentService.findById(studentId);
        if(theStudent == null){
            throw new NoSuchElementException("The student with id " + studentId + " was not found.\n");
        }
        return theStudent;
    }

    @PostMapping
    public Student addStudent( @RequestBody UserInfoDto userInfoDto){
        return studentService.save(userInfoDto);
    }

    @PutMapping("/{studentId}/profile")
    public Student updateProfile(@PathVariable Long studentId,@RequestBody ProfileDto profileDto){
        return studentService.updateProfile(studentId,profileDto);
    }

    @DeleteMapping("/{studentId}")
    public Student deleteStudent(@PathVariable long studentId) {
        Student theStudent = this.studentService.findById(studentId);

        if (theStudent == null) {
            throw new NoSuchElementException("The student with id " + studentId + " was not found.\n");
        }

        this.studentService.deleteById(studentId);
        return theStudent;
    }
}



