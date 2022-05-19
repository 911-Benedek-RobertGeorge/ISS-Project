package com.academic.ISSProject.service;

import com.academic.ISSProject.domain.Enroll;
import com.academic.ISSProject.domain.Grade;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;

import java.util.List;

public interface IStudentService {

    public List<Student> findAll();

    public Student findById(long id);

    public Student save(UserInfoDto userInfo);

    public void deleteById(long id);


    Student updateProfile(Long studentId, ProfileDto profileDto);

    List<Grade> getGradesForStudent(Long studentId);

 }
