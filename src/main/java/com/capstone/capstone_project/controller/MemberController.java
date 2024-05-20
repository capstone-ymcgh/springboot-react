package com.capstone.capstone_project.controller;

import com.capstone.capstone_project.dto.request.member.MemberLoginDTO;
import com.capstone.capstone_project.dto.request.member.MemberUpdateDTO;
import com.capstone.capstone_project.dto.response.member.MemberTokenDTO;
import com.capstone.capstone_project.entity.MemberEntity;
import com.capstone.capstone_project.service.MemberSerivce;
import com.capstone.capstone_project.dto.request.member.MemberRegisterDTO;
import com.capstone.capstone_project.dto.response.member.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSerivce memberService;

    @GetMapping("/checkId")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String email) {
        memberService.checkIdDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDTO> register(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        MemberResponseDTO successMember = memberService.register(memberRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberTokenDTO> login(@RequestBody MemberLoginDTO memberLoginDTO) {
        MemberTokenDTO loginDTO = memberService.login(memberLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).header(loginDTO.getToken()).body(loginDTO);
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<MemberResponseDTO> check(
            @AuthenticationPrincipal MemberEntity member,
            @RequestBody Map<String, String> request) {
        String password = request.get("password");
        MemberResponseDTO memberInfo = memberService.check(member, password);
        return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
    }

    @PutMapping("/update")
    public ResponseEntity<MemberResponseDTO> update(
            @AuthenticationPrincipal MemberEntity member,
            @RequestBody MemberUpdateDTO memberUpdateDTO) {
        MemberResponseDTO memberUpdate = memberService.update(member, memberUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdate);
    }
}
