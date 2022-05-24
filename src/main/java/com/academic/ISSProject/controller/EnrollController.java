package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.Enroll;
import com.academic.ISSProject.domain.Specialization;
import com.academic.ISSProject.domain.dto.CurriculumDto;
import com.academic.ISSProject.domain.dto.SpecializationDto;
import com.academic.ISSProject.service.implementation.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<CurriculumDto> getCurriculumOfSpecialization(@PathVariable Long specializationId){
        List<Curriculum> curriculumList = enrollService.getCurriculumsOfSpecialization(specializationId);
        List<CurriculumDto> result = new ArrayList<>();
        curriculumList.forEach((value) -> {
            result.add(new CurriculumDto(value.getId(), value.getYear(), value.getCurriculumName(), value.getLanguage(), value.getSpecialization().getId()));
        });
        return result;
    }

    @GetMapping("/curriculums/{currId}/courses")
    public List<Course> getCoursesOfCurriculum(@PathVariable Long currId){
        return enrollService.getCoursesOfCurriculum(currId);
    }

    @GetMapping("/student/{studentId}/specialization/{specializationId}")
    public Boolean checkIfEnrolled(@PathVariable Long studentId,@PathVariable Long specializationId){
    return enrollService.checkIfEnrolled(studentId,specializationId);
    }

    @PostMapping("/student/{studentId}/specialization/{specializationId}")
    public Enroll enrollToSpecialization(@PathVariable Long studentId,@PathVariable Long specializationId){
        return enrollService.enrollToSpecialization(studentId,specializationId);
    }
}
