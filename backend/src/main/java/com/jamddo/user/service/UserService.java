package com.jamddo.user.service;

import com.jamddo.global.exception.CustomException;
import com.jamddo.user.domain.User;
import com.jamddo.user.dto.LoginDto;
import com.jamddo.user.dto.SignupDto;
import com.jamddo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jamddo.global.exception.ErrorCode.ALREADY_REGISTERED_MEMBER;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository memberRepository;
    @Transactional
    public void signup(SignupDto signupDto) {
        // 이미 존재하는 아이디
        if(memberRepository.existsByNickname(signupDto.getNickname())){
            throw new CustomException(ALREADY_REGISTERED_MEMBER);
        }
        // 아이디 등록
        User user = User.builder()
                .nickname(signupDto.getNickname())
                .password(signupDto.getPassword())
                .build();

        memberRepository.save(user);

    }

    @Transactional
    public void login(LoginDto loginDto) {
    }
}
