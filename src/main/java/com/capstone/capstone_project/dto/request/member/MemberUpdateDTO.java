package com.capstone.capstone_project.dto.request.member;

// 회원 정보 변경

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDTO {
    private String password;
    private String confirmpassword;
    private String username;

    @Builder
    public MemberUpdateDTO(String password, String confirmpassword, String username) {
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.username = username;
    }
}
