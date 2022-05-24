package com.academic.ISSProject.service;

import com.academic.ISSProject.domain.Staff;
import com.academic.ISSProject.domain.dto.AverageDto;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.StudentGradeDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;

import java.util.List;

public interface IStaffService {
    List<Staff> findAll();

    Staff findById(long id);

    Staff save(UserInfoDto userInfoDto);

    void deleteById(long id);

    Staff updateProfile(Long staffId, ProfileDto profileDto);

    List<StudentGradeDto> getStudentsOrderedByResults();

    List<AverageDto> getAllStudentsSortedByAverageInYear(Long year);
}
