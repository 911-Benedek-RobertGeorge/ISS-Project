package com.academic.ISSProject.service;

import com.academic.ISSProject.domain.Course;
import com.academic.ISSProject.domain.Grade;
import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.dto.CourseDto;
import com.academic.ISSProject.domain.dto.GradeDto;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;

import java.util.List;

public interface ITeacherService {
    List<Teacher> findAll();

    Teacher findById(long id);

    Teacher save(UserInfoDto userInfoDto);

    void deleteById(long id);

    Teacher updateProfile(Long teacherId, ProfileDto profileDto);

    Grade postGrade(Long teacherId,
                    Long studentId,
                    Long courseId,
                    Integer grade);


    Course proposeOptionalCourse(Long teacherId, Long curriculumId, CourseDto courseDto);
    List<Course> getCourses(Long teacherId);
    List<GradeDto> getGradesOfCourse(Long courseId);
}
