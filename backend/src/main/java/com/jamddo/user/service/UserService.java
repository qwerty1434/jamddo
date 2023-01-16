package com.jamddo.user.service;

import com.jamddo.global.exception.CustomException;
import com.jamddo.user.domain.User;
import com.jamddo.user.dto.LoginDto;
import com.jamddo.user.dto.UserResponseDto;
import com.jamddo.user.dto.RankingDto;
import com.jamddo.user.dto.SignupDto;
import com.jamddo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jamddo.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupDto signupDto) {
        // 이미 존재하는 아이디
        if(userRepository.existsByNickname(signupDto.getNickname())){
            throw new CustomException(ALREADY_REGISTERED_MEMBER);
        }
        // 아이디 등록
        User user = User.builder()
                .nickname(signupDto.getNickname())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .point(0L)
                .build();

        userRepository.save(user);

    }

    @Transactional
    public UserResponseDto login(LoginDto loginDto) {
        User user = userRepository.findByNickname(loginDto.getNickname()).orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));

        if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){ // 패스워드 비교 시 equals가 아니라 matches로 비교

            return UserResponseDto.builder().nickname(user.getNickname()).point(user.getPoint()).build(); // 로그인 성공
        }else{
            throw new CustomException(NOT_CORRECT_PASSWORD);
        }
    }


    @Transactional
    public void resetPoint(LoginDto loginDto){
        User user = userRepository.findByNickname(loginDto.getNickname()).orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));
        user.pointReset();
    }

    @Transactional
    public void substractPoint(LoginDto loginDto){
        User user = userRepository.findByNickname(loginDto.getNickname()).orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));
        user.substractPoint();
    }

    @Transactional
    public List<RankingDto> ranking(){
        return userRepository.findAllOrderByPriceDesc();
    }


}
