package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.Profile;
import com.academic.ISSProject.domain.Role;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.UserInfo;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.repository.ProfileRepository;
import com.academic.ISSProject.repository.StudentRepository;
import com.academic.ISSProject.repository.UserInfoRepository;
import com.academic.ISSProject.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class StudentService implements IStudentService  {
    private final StudentRepository studentRepository;
    private final UserInfoRepository userInfoRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public StudentService(StudentRepository studentRepository, UserInfoRepository userInfoRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userInfoRepository = userInfoRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public List<Student> findAll() {
        log.info("Fetching all students\n");
       return studentRepository.findAll();
    }

    @Override
    public Student findById(long id) {
        log.info("Get the user with id " + id + "\n");
        Optional<Student> opt = studentRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            return null;
    }

    @Override
    public Student save(UserInfoDto userInfoDto) {
        log.info("saving a new student  \n");

        UserInfo userinfo = new UserInfo(userInfoDto);

        Student student = new Student();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("STUDENT"));
        //userinfo.setRoles(roles);
        userinfo.setRole("STUDENT");
        userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
        userinfo = userInfoRepository.save(userinfo);

        student.setUserInfo(userinfo);
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(long id) {
        log.info("Deleting the user with id " + id + "\n");

        studentRepository.deleteById(id);
    }

    @Override
    public Student updateProfile(Long studentId, ProfileDto profileDto) {
        log.info("Update the student profile with id " + studentId + "\n");

        Profile profile = new Profile(profileDto);
        profile =  profileRepository.save(profile);
        Student theStudent = studentRepository.getById(studentId);
        theStudent.setProfile(profile );

        return  studentRepository.save(theStudent);
    }
}
