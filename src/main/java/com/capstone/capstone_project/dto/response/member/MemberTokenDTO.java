package com.capstone.capstone_project.dto.response.member;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

// 회원 정보 반환 + token DTO
@Getter
@Setter
@NoArgsConstructor
public class MemberTokenDTO {
    private String email;
    private String token;

    @Builder
    public MemberTokenDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }

    // Entity -> DTO
    public static MemberTokenDTO fromEntity(UserDetails member, String token) {
        return MemberTokenDTO.builder()
                .email(member.getUsername())
                .token(token)
                .build();
    }
}
