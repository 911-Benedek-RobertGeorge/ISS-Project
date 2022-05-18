package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.Specialization;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.service.implementation.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enroll")
@CrossOrigin(origins = "http://localhost:4200")
public class EnrollController {
    private final EnrollService enrollService;

    @Autowired
    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }


    @GetMapping("/specializations/{studentId}")
    public List<SpecializationDto> getAllSpecializations(@PathVariable Long studentId){
        return enrollService.getAllSpecializations(studentId);
    }

    @GetMapping("/specializations")
    public List<Specialization> getSpecialization(){
        return enrollService.getSpecializations();
    }

    @GetMapping("/specializations/curriculums/{specializationId}")
    public List<Curriculum> getCurriculumOfSpecialization(@PathVariable Long specializationId){
        return enrollService.getCurriculumsOfSpecialization(specializationId);
    }

    @GetMapping("/curriculums/{currId}/courses")
    public List<Course> getCoursesOfCurriculum(@PathVariable Long currId){
        return enrollService.getCoursesOfCurriculum(currId);
    }

}
