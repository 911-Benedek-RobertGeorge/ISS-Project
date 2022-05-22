package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.*;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.StudentGradeDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.repository.ProfileRepository;
import com.academic.ISSProject.repository.StaffRepository;
import com.academic.ISSProject.repository.StudentRepository;
import com.academic.ISSProject.repository.UserInfoRepository;
import com.academic.ISSProject.service.IStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StaffService implements IStaffService {

    private final StaffRepository staffRepository;
    private final UserInfoRepository userInfoRepository;
    private final ProfileRepository profileRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StaffService(StaffRepository staffRepository, UserInfoRepository userInfoRepository, ProfileRepository profileRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.userInfoRepository = userInfoRepository;
        this.profileRepository = profileRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public List<Staff> findAll() {
        log.info("Get all staff");
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(long id) {
        log.info("Get staff with id= " + id + '\n');
        Optional<Staff> opt = staffRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            return null;
    }

    @Override
    public Staff save(UserInfoDto userInfoDto) {

        UserInfo userinfo = new UserInfo(userInfoDto);

        userinfo.setRole("STAFF");
        userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));

        userinfo = userInfoRepository.save(userinfo);

        Staff staff = new Staff();
        staff.setUserInfo(userinfo);

        return staffRepository.save(staff);
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete staff with id= " + id + "\n");
        staffRepository.deleteById(id);
    }

    @Override
    public Staff updateProfile(Long staffId, ProfileDto profileDto) {
        log.info("Update profile staff's profile with id= " + staffId + "\n");
        Profile profile = new Profile(profileDto);
        profile =  profileRepository.save(profile);

        Staff staff = staffRepository.getById(staffId);
        staff.setProfile(profile);

        return staffRepository.save(staff);
    }

    @Override
    public List<StudentGradeDto> getStudentsOrderedByResults() {
        List<Student> sortedStudList = studentRepository.findAll();
        List<StudentGradeDto> result = new ArrayList<>();

        sortedStudList.forEach((value)->{
            Double average = value.getGrades().stream().mapToDouble(Grade::getGrade).average().orElse(0.0);
            result.add(new StudentGradeDto(value, average));
        });

        result.sort(Comparator.comparing(StudentGradeDto::getAverage));

        return result;
    }
}
