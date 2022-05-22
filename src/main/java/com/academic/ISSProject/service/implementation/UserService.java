package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.Staff;
import com.academic.ISSProject.domain.Student;
import com.academic.ISSProject.domain.Teacher;
import com.academic.ISSProject.domain.UserInfo;
import com.academic.ISSProject.domain.dto.UserInfoDto;
import com.academic.ISSProject.repository.StaffRepository;
import com.academic.ISSProject.repository.StudentRepository;
import com.academic.ISSProject.repository.TeacherRepository;
import com.academic.ISSProject.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements UserDetailsService {

    protected final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final TeacherRepository teacherRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoRepository.findByUsername(username);

        if(user ==null ){
            log.info("User not found in db");
            throw new UsernameNotFoundException("User not found in db");
        }
        else{
            log.info("User found in db " + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public UserInfo getUserInfoByUsername(String username){
        return userInfoRepository.findByUsername(username);
    }

    public String register(UserInfoDto userInfo){
        log.info("saving a new User  \n");

        UserInfo userinfo = new UserInfo(userInfo);
        userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));

        if(userInfoRepository.findByUsername(userInfo.getUsername()) != null)
            throw new IllegalArgumentException("This username is taken.");

        userinfo = userInfoRepository.save(userinfo);
        if(userInfo.getRole().equals("STUDENT"))
        {
            Student student = new Student();
            student.setUserInfo(userinfo);
            studentRepository.save(student);
        }else if(userInfo.getRole().equals("TEACHER"))
        {
            Teacher teacher = new Teacher();
            teacher.setUserInfo(userinfo);
            teacherRepository.save(teacher);
        }else if(userInfo.getRole().equals("STAFF")){
            Staff staff = new Staff();
            staff.setUserInfo(userinfo);
            staffRepository.save(staff);
        } else {
            throw new IllegalArgumentException("The role was not identified.");
        }

        return "Successful registered! ";
    }
}
