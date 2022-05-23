package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.*;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.repository.*;
import com.academic.ISSProject.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Slf4j
public class StudentService implements IStudentService  {
    private final StudentRepository studentRepository;
    private final UserInfoRepository userInfoRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;
    private final ContractRepository contractRepository;

    ///TODO ENCODE STAFF AND TEACHER TOo

    @Autowired
    public StudentService(StudentRepository studentRepository, UserInfoRepository userInfoRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder, CourseRepository courseRepository,
                          ContractRepository contractRepository) {
        this.studentRepository = studentRepository;
        this.userInfoRepository = userInfoRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
        this.courseRepository = courseRepository;
        this.contractRepository = contractRepository;
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
   // @PreAuthorize("authentication.principal.username == #username")
    public Student updateProfile(Long studentId, ProfileDto profileDto, String username) {
        log.info("Update the student profile with id " + studentId + "\n");

        Profile profile = new Profile(profileDto);
        profile =  profileRepository.save(profile);
        Student theStudent = studentRepository.getById(studentId);
        if(!theStudent.getUserInfo().getUsername().equals(username))
        {
            throw new SecurityException("Security Exception, you cant modify someone else profile");
        }
        theStudent.setProfile(profile);

        return  studentRepository.save(theStudent);
    }
    @Override
    public List<Grade> getGradesForStudent(Long studentId){
        log.info("get grades for the student  with id " + studentId + "\n");

        Student student = studentRepository.getById(studentId);
        if(student != null){
            return student.getGrades();
        }
        else
        {
            throw new NoSuchElementException("Student with id " + studentId + " was not found");
        }
    }

    public List<Student> getStudentsOfACourse(Long courseId){
        return this.contractRepository.getAllStudentsOfACurriculum(courseRepository.getById(courseId).getCurriculum().getId());
    }
}
