package com.capstone.capstone_project.service;


import com.capstone.capstone_project.common.exception.MemberException;
import com.capstone.capstone_project.common.exception.ResourceNotFoundException;
import com.capstone.capstone_project.dto.request.member.MemberLoginDTO;
import com.capstone.capstone_project.dto.request.member.MemberRegisterDTO;
import com.capstone.capstone_project.dto.request.member.MemberUpdateDTO;
import com.capstone.capstone_project.dto.response.member.MemberResponseDTO;
import com.capstone.capstone_project.dto.response.member.MemberTokenDTO;
import com.capstone.capstone_project.entity.MemberEntity;
import com.capstone.capstone_project.repository.MemberRepository;
import com.capstone.capstone_project.security.jwt.CustomMemberDetailsService;
import com.capstone.capstone_project.security.jwt.JwtTokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberSerivce {
    private final PasswordEncoder encoder;
    private final MemberRepository memberRepository;

    private final AuthenticationManager authenticationManager;
    private final CustomMemberDetailsService memberDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public HttpStatus checkIdDuplicate(String email) {
        isExistUserEmail(email);
        return HttpStatus.OK;
    }

    public MemberResponseDTO register(MemberRegisterDTO registerDto) {
        isExistUserEmail(registerDto.getEmail());
        checkPassword(registerDto.getPassword(), registerDto.getConfirmpassword());

        // 패스워드 암호화
        String encodePwd = encoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodePwd);

        MemberEntity saveMember = memberRepository.save(
                MemberRegisterDTO.ofEntity(registerDto));

        return MemberResponseDTO.fromEntity(saveMember);
    }


    public MemberTokenDTO login(MemberLoginDTO loginDto) {
        authenticate(loginDto.getEmail(), loginDto.getPassword());
        UserDetails userDetails = memberDetailsService.loadUserByUsername(loginDto.getEmail());
        checkEncodePassword(loginDto.getPassword(), userDetails.getPassword());
        String token = jwtTokenUtil.generateToken(userDetails);
        return MemberTokenDTO.fromEntity(userDetails, token);
    }

    public MemberResponseDTO check(MemberEntity member, String password) {
        MemberEntity checkMember = (MemberEntity) memberDetailsService.loadUserByUsername(member.getEmail());
        checkEncodePassword(password, checkMember.getPassword());
        return MemberResponseDTO.fromEntity(checkMember);
    }

    public MemberResponseDTO update(MemberEntity member, MemberUpdateDTO updateDto) {
        checkPassword(updateDto.getPassword(), updateDto.getConfirmpassword());
        String encodePwd = encoder.encode(updateDto.getPassword());
        MemberEntity updateMember =  memberRepository.findByEmail(member.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Email", member.getEmail())
        );
        updateMember.update(encodePwd, updateDto.getUsername());
        return MemberResponseDTO.fromEntity(updateMember);
    }

    /**
     * 사용자 인증
     * @param email
     * @param pwd
     */
    private void authenticate(String email, String pwd) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
        } catch (DisabledException e) {
            throw new MemberException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 아이디(이메일) 중복 체크
     * @param email
     */
    private void isExistUserEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 비밀번호와 비밀번호 확인이 같은지 체크
     * @param password
     * @param passwordCheck
     */
    private void checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 사용자가 입력한 비번과 DB에 저장된 비번이 같은지 체크 : 인코딩 확인
     * @param rawPassword
     * @param encodedPassword
     */
    private void checkEncodePassword(String rawPassword, String encodedPassword) {
        if (!encoder.matches(rawPassword, encodedPassword)) {
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }
}
