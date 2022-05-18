package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.service.implementation.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enroll")
public class EnrollController {
    private final EnrollService enrollService;

    @Autowired
    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @GetMapping("/specializations")
    public List<SpecializationDto> getAllSpecializations(){
        return enrollService.getAllSpecializations();
    }

    @GetMapping("/specializations/{specializationId}")
    public List<Curriculum> getCurriculumOfSpecialization(@PathVariable Long specializationId){
        return enrollService.getCurriculumsOfSpecialization(specializationId);
    }

    @GetMapping("/curriculums/{currId}/courses")
    public List<Course> getCoursesOfCurriculum(@PathVariable Long currId){
        return enrollService.getCoursesOfCurriculum(currId);
    }

    @GetMapping("/student/{studentId}/specialization/{specializationId}")
    public Boolean checkIfEnrolled(@PathVariable Long studentId,@PathVariable Long specializationId){
    return false;
    }

}
