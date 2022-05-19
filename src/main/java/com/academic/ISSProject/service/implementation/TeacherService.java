package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.*;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.repository.*;
import com.academic.ISSProject.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final UserInfoRepository userInfoRepository;
    private final ProfileRepository profileRepository;
    private final CourseRepository courseRepository;
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, UserInfoRepository userInfoRepository, ProfileRepository profileRepository, CourseRepository courseRepository, GradeRepository gradeRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.userInfoRepository = userInfoRepository;
        this.profileRepository = profileRepository;
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Teacher> findAll() {
        log.info("Get all users \n");
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(long id) {
        log.info("Get the user with id " + id + "\n");
        Optional<Teacher> opt = teacherRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Teacher save(UserInfoDto userInfoDto) {
        log.info("Save the user with userInfo: " + userInfoDto.toString() + "\n");
        UserInfo userInfo = new UserInfo(userInfoDto);
        userInfo = userInfoRepository.save(userInfo);

        Teacher teacher = new Teacher();
        teacher.setUserInfo(userInfo);

        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete the user with id " + id + "\n");
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updateProfile(Long teacherId, ProfileDto profileDto) {
        log.info("Update profile with id: " + teacherId + "with profile data: " + profileDto.toString() + "\n");
        Profile profile = new Profile(profileDto);
        profile = profileRepository.save(profile);

        Teacher teacher = teacherRepository.getById(teacherId);
        teacher.setProfile(profile);

        return teacherRepository.save(teacher);
    }

    @Override
    public Grade postGrade(Long teacherId,
                           Long studentId,
                           Long courseId,
                           Integer grade){
        log.info("Teacher grading the student with id " + studentId +" grade " + grade );
        Course course = courseRepository.getById(courseId);
        if(course == null){
            throw new NoSuchElementException("course with id " + courseId + " was not found");
        }
        if(course.getTeacherId() != teacherId){
            throw new RuntimeException("The teacher is trying to grade for a course that he dont teach on");
        }
        Student student = studentRepository.getById(studentId);
        if(student == null){
            throw new NoSuchElementException("student with id "+ studentId + " was not found");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
       Date date =  new Date(dtf.format(now));
        log.info("the grading date is : " + date);
        Grade gradeObj = new Grade(0L,grade,date,student,course);
        return gradeRepository.save(gradeObj);
    }
}
