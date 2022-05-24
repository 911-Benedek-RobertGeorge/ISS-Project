package com.academic.ISSProject.controller;


import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.Enroll;
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

    /**
     * Endpoint that receives the specialisation of a specific student
     * @param studentId student's id
     * @return SpecializationDto
     */
    @GetMapping("/specializations/{studentId}")
    public List<SpecializationDto> getAllSpecializations(@PathVariable Long studentId){
        return enrollService.getAllSpecializations(studentId);
    }

    /**
     * Endpoint that receives the list with all Specialisations
     * @return List<SpecialisationDto>
     */
    @GetMapping("/specializations")
    public List<Specialization> getSpecialization(){
        return enrollService.getSpecializations();
    }

    /**
     * Endpoint that receives the curriculums of a specialisation
     * @param specializationId specialisation's id
     * @return List<Curriculum>
     */
    @GetMapping("/specializations/curriculums/{specializationId}")
    public List<Curriculum> getCurriculumOfSpecialization(@PathVariable Long specializationId){
        return enrollService.getCurriculumsOfSpecialization(specializationId);
    }

    /**
     * Endpoint that receives the courses of a curriculum
     * @param currId curriculum's id
     * @return List<Course>
     */
    @GetMapping("/curriculums/{currId}/courses")
    public List<Course> getCoursesOfCurriculum(@PathVariable Long currId){
        return enrollService.getCoursesOfCurriculum(currId);
    }

    /**
     * Endpoint that checks if a student is enrolled to a specialisation
     * @param studentId student's id
     * @param specializationId specialisation's id
     * @return Bool true of false
     */
    @GetMapping("/student/{studentId}/specialization/{specializationId}")
    public Boolean checkIfEnrolled(@PathVariable Long studentId,@PathVariable Long specializationId){
    return enrollService.checkIfEnrolled(studentId,specializationId);
    }

    /**
     * Endpoint that enroll student to a specialisation
     * @param studentId student's id
     * @param specializationId specialisation's id
     * @return enroll object
     */
    @PostMapping("/student/{studentId}/specialization/{specializationId}")
    public Enroll enrollToSpecialization(@PathVariable Long studentId,@PathVariable Long specializationId){
        return enrollService.enrollToSpecialization(studentId,specializationId);
    }
}
