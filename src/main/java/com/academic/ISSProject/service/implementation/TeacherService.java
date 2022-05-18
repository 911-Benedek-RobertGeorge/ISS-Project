package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.Profile;
import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.UserInfo;
import com.academic.ISSProject.domain.dto.ProfileDto;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.repository.ProfileRepository;
import com.academic.ISSProject.repository.TeacherRepository;
import com.academic.ISSProject.repository.UserInfoRepository;
import com.academic.ISSProject.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final UserInfoRepository userInfoRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, UserInfoRepository userInfoRepository, ProfileRepository profileRepository) {
        this.teacherRepository = teacherRepository;
        this.userInfoRepository = userInfoRepository;
        this.profileRepository = profileRepository;
    }


    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(long id) {
        Optional<Teacher> opt = teacherRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Teacher save(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo(userInfoDto);
        userInfo = userInfoRepository.save(userInfo);

        Teacher teacher = new Teacher();
        teacher.setUserInfo(userInfo);

        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteById(long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updateProfile(Long teacherId, ProfileDto profileDto) {
        Profile profile = new Profile(profileDto);
        profile = profileRepository.save(profile);

        Teacher teacher = teacherRepository.getById(teacherId);
        teacher.setProfile(profile);

        return teacherRepository.save(teacher);
    }
}
