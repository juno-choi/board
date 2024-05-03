package com.juno.simple.member.service;

import com.juno.simple.member.domain.dto.JoinRequest;
import com.juno.simple.member.domain.entity.MemberEntity;
import com.juno.simple.member.domain.response.JoinResponse;
import com.juno.simple.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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

        return JoinResponse.builder()
                .memberId(saveMember.getMemberId())
                .build();
    }
}
