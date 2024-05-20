package com.capstone.capstone_project.dto.response.member;


import com.capstone.capstone_project.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 회원 정보 반환
@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDTO {
    // 회원 DB 인덱스 값을 회원에게 노출시킬 필요 x
    private String email;
    private String username;

    @Builder
    public MemberResponseDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    // Entity -> DTO
    public static MemberResponseDTO fromEntity(MemberEntity member) {
        return MemberResponseDTO.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .build();
    }
}
