package com.juno.simple.member.service;

import com.juno.simple.config.security.TokenProvider;
import com.juno.simple.member.domain.dto.JoinRequest;
import com.juno.simple.member.domain.dto.LoginRequest;
import com.juno.simple.member.domain.entity.MemberEntity;
import com.juno.simple.member.domain.response.JoinResponse;
import com.juno.simple.member.domain.response.LoginResponse;
import com.juno.simple.member.domain.response.MemberResponse;
import com.juno.simple.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional
    public JoinResponse join(JoinRequest joinRequest) {
        // 회원 요효성 검사
        Optional<MemberEntity> findMember = memberRepository.findByEmail(joinRequest.getEmail());
        if (findMember.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        // 비밀번호 암호화
        joinRequest.encryptPassword(passwordEncoder);
        //  회원가입
        MemberEntity saveMember = memberRepository.save(joinRequest.toEntity());

        return JoinResponse.from(saveMember);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        // security에 구현한 MyUserDetailsService 실행됨
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 토큰 생성
        return tokenProvider.createToken(authenticate);
    }

    public MemberResponse getMember(HttpServletRequest request) {
        Authentication authorization = tokenProvider.getAuthentication(request.getHeader("Authorization"));
        Long memberId = Long.parseLong(authorization.getName());
        MemberEntity findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않는 회원입니다."));
        return MemberResponse.from(findMember);
    }
}
