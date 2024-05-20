package com.capstone.capstone_project.dto.request.member;

//회원 가입 요청.

import com.capstone.capstone_project.common.Role;
import com.capstone.capstone_project.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDTO {

    private String email;
    private String password;
    private String confirmpassword;
    private String username;

    @Builder
    public MemberRegisterDTO(String email, String password, String confirmpassword, String username) {
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.username = username;
    }

    // DTO -> Entity
    public static MemberEntity ofEntity(MemberRegisterDTO dto) {
        return MemberEntity.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .roles(Role.USER)
                .build();
    }

}
