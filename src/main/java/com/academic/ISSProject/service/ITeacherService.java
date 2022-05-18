package com.academic.ISSProject.service;

import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;

import java.util.List;

public interface ITeacherService {
    List<Teacher> findAll();

    Teacher findById(long id);

    Teacher save(UserInfoDto userInfoDto);

    void deleteById(long id);

    Teacher updateProfile(Long teacherId, ProfileDto profileDto);
}
