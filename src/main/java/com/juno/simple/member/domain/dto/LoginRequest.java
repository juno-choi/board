package com.juno.simple.member.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(description = "로그인 이메일", example = "simple@mail.com")
    @Email(message = "email 형식을 확인해 주세요.")
    private String email;

    @Schema(description = "비밀번호", example = "123456")
    @Length(min = 5, max = 20, message = "password는 5~20글자 입니다.")
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
