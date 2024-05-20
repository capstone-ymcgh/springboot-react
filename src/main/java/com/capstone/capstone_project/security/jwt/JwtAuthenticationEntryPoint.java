package com.capstone.capstone_project.security.jwt;

/*
 * 인증되지 않은 사용자가 보호된 리소스에 액세스하려고 할 때 발생하는 예외를 처리하는 데 사용.
 * 여기서는 SC_UNAUTHORIZED(401 Unauthorized) 상태 코드를 응답으로 보내고 "Unauthorized" 메시지를 함께 전송
 */

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
