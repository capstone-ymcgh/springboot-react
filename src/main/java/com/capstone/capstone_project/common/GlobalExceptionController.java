package com.capstone.capstone_project.common;

import com.capstone.capstone_project.common.exception.MemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 멤버 예외 처리를 하기 위한 전역 예외 컨트롤러
 */

public class GlobalExceptionController {
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<String> handleMemberException(MemberException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
