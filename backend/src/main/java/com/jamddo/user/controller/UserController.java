package com.jamddo.user.controller;

import com.jamddo.global.exception.CustomException;
import com.jamddo.global.exception.CustomValidationException;
import com.jamddo.user.dto.LoginDto;
import com.jamddo.user.dto.SignupDto;
import com.jamddo.user.service.AuthService;
import com.jamddo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.jamddo.global.exception.ErrorCode.NOT_EQUAL_PASSWORD;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupDto signupDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {

            if (!signupDto.getPassword().equals(signupDto.getPasswordConfirm())) {
                throw new CustomException(NOT_EQUAL_PASSWORD);
            }
            userService.signup(signupDto);
            return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공했습니다.");
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(authService.authorize(loginDto))
                    .body(userService.login(loginDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 로그아웃
    
    // 토큰을 함께 전송했을 때 유저정보 반환
    @GetMapping("/state")
    public ResponseEntity state(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.userState());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    

    // 포인트 차감
    @PostMapping("/substractpoint")
    public ResponseEntity substractPoint(){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(userService.substractPoint());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 포인트 리셋
    @GetMapping("/resetpoint")
    public ResponseEntity resetPoint(){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(userService.resetPoint());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity ranking(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    userService.ranking()
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
