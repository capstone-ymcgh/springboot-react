package com.capstone.capstone_project.dto.request.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// 로그인 요청.
@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDTO {

    private String email;
    private String password;

    @Builder
    public MemberLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
