package com.jamddo.user.service;

import com.jamddo.user.domain.User;
import com.jamddo.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        // DB에서 권한정보와 유저정보를 가져옴
        return userRepository.findByNickname(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }


    private org.springframework.security.core.userdetails.User createUser(String username, User user) {


        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(); // null을 넣으면 값이 안나옴, 빈값이라도 new ArrayList<>()로 해야 함

        // 유저의 권한정보, username, password를 가지고 Userdetails의 User객체를 리턴함
        return new org.springframework.security.core.userdetails.User(user.getNickname(),
                user.getPassword(),
                grantedAuthorities);
    }
}