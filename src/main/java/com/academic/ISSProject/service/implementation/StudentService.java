package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.Profile;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.UserInfo;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.repository.ProfileRepository;
import com.academic.ISSProject.repository.StudentRepository;
import com.academic.ISSProject.repository.UserInfoRepository;
import com.academic.ISSProject.service.IStudentService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final UserInfoRepository userInfoRepository;
    private final ProfileRepository profileRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository, UserInfoRepository userInfoRepository, ProfileRepository profileRepository) {
        this.studentRepository = studentRepository;
        this.userInfoRepository = userInfoRepository;
        this.profileRepository = profileRepository;
    }



    @Override
    public List<Student> findAll() {
       return studentRepository.findAll();
    }

    @Override
    public Student findById(long id) {
        Optional<Student> opt = studentRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            return null;
    }

    @Override
    public Student save(UserInfoDto userInfoDto) {
        UserInfo userinfo = new UserInfo(userInfoDto);
        userinfo = userInfoRepository.save(userinfo);
        Student student = new Student();
        student.setUserId(userinfo.getId());
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateProfile(Long studentId, ProfileDto profileDto) {
        Profile profile = new Profile(profileDto);
        profile =  profileRepository.save(profile);
        Student theStudent = studentRepository.getById(studentId);
        theStudent.setProfileId(profile.getId());

        return  studentRepository.save(theStudent);
    }
}
