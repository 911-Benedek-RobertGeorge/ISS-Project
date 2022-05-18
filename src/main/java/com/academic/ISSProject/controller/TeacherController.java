package com.academic.ISSProject.controller;

import com.academic.ISSProject.domain.Profile;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.service.implementation.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
