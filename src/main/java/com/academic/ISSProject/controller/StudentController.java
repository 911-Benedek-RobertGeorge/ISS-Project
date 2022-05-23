package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Grade;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.SimpleStudentDto;
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
import java.util.ArrayList;
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
    public Student addStudent(@RequestBody UserInfoDto userInfoDto){
        return studentService.save(userInfoDto);
    }
    public User getCurrentUser(Principal principal) {

        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

    @PutMapping("/{studentId}/profile")
    public Student updateProfile(@PathVariable Long studentId,@RequestBody ProfileDto profileDto ,Principal principal  ){

        String username = principal.getName();
        return studentService.updateProfile(studentId,profileDto,username);
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
    @GetMapping("/{studentId}/grades")
    public List<Grade> getGradesForStudent(@PathVariable Long studentId){
        return  studentService.getGradesForStudent(studentId);
    }

    @GetMapping("@GetMapping('/{courseId}/course')\n" +
            "    public List<Grade> getGradesOfCourse(@PathVariable Long courseId){\n" +
            "        return \n" +
            "    }/{courseId}/course")
    public List<SimpleStudentDto> getStudentsOfACourse(@PathVariable Long courseId){
        List<Student> students =  studentService.getStudentsOfACourse(courseId);
        List<SimpleStudentDto> result = new ArrayList<>();

        students.forEach((value) -> {
            result.add(new SimpleStudentDto(value.getId(), value.getUserInfo().getFirstName(), value.getUserInfo().getLastName()));
        });

        return result;
    }
}



