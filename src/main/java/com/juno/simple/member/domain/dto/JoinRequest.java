package com.juno.simple.member.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.juno.simple.member.domain.entity.MemberEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JoinRequest {
    @Schema(description = "가입 이메일", example = "simple@mail.com")
    @Email(message = "email 형식을 확인해 주세요.")
    private String email;

    @Schema(description = "비밀번호", example = "123456")
    @Length(min = 5, max = 20, message = "password는 5~20글자 입니다.")
    private String password;

    @Schema(description = "비밀번호 확인", example = "123456")
    @Length(min = 5, max = 20, message = "password는 5~20글자 입니다.")
    @NotNull(message = "password_check는 비어있을 수 없습니다.")
    private String passwordCheck;

    @Schema(description = "이름", example = "최심플")
    @NotNull(message = "password_check는 비어있을 수 없습니다.")
    private String name;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

    public void encryptPassword(BCryptPasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
