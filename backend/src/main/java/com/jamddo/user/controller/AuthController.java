package com.jamddo.user.controller;

import com.jamddo.global.jwt.JwtFilter;
import com.jamddo.global.jwt.TokenProvider;
import com.jamddo.user.dto.LoginDto;
import com.jamddo.user.dto.TokenDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("authenticate 시작!!!!!!!!!!!!!!!!");
        // username, password를 통해 AuthenticationToken객체를 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getNickname(), loginDto.getPassword());
        System.out.println("authenticationToken = " + authenticationToken);
        // authentication 토큰을 이용해 authenticate 메서드가 실행될 때 CustomUserDetailsService의 loadUserByUsername이 실행됨.
        // 실행된 결과값을 가지고 authentication 결과값을 생성하게 됨

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("authentication = " + authentication);
        // 생성된 authentication 객체를 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // tokenProvider를 통해 토큰 생성
        String jwt = tokenProvider.createToken(authentication);

        // jwt token을 response header에 넣어서 보내줌
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        System.out.println("jwt = " + jwt+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // jwt token을 TokenDto를 통해 body에 넣어서 보내줌
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}