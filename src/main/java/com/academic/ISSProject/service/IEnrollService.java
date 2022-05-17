package com.academic.ISSProject.service;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Curriculum;
import com.academic.ISSProject.domain.dto.SpecializationDto;

import java.util.List;

public interface IEnrollService {
    List<SpecializationDto> getAllSpecializations();

    List<Curriculum> getCurriculumsOfSpecialization(long specializationId);

    List<Course> getCoursesOfCurriculum(Long currId);
}
