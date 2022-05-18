package com.academic.ISSProject.service;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.Specialization;
import com.academic.ISSProject.domain.dto.SpecializationDto;

import java.util.List;

public interface IEnrollService {
    List<SpecializationDto> getAllSpecializations(Long studentId);

    List<Specialization> getSpecializations();

    List<Curriculum> getCurriculumsOfSpecialization(long specializationId);

    List<Course> getCoursesOfCurriculum(Long currId);


    Boolean checkIfEnrolled(Long studentId, Long specializationId);
}
