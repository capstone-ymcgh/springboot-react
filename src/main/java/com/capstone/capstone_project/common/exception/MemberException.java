package com.capstone.capstone_project.common.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
/*
 *멤버 예외 처리를 위한 커스텀 예외 클래스를 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public MemberException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
