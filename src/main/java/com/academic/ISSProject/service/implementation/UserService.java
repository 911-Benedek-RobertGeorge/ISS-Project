package com.academic.ISSProject.service.implementation;

import com.academic.ISSProject.domain.UserInfo;
import com.academic.ISSProject.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {

    protected final UserInfoRepository userInfoRepository;

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
}
