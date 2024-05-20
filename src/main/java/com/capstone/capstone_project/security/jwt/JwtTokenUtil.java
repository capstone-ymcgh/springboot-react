package com.capstone.capstone_project.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/*
 * JwtTokenUtil은 JWT 토큰을 생성하고, 검증하며, 토큰에서 필요한 정보를 추출하는 데 사용
 * generateToken: UserDetails 객체에서 사용자 정보를 추출하여 클레임을 생성, 이를 이용하여 JWT 토큰을 생성
 * doGenerateToken: JWT 토큰의 클레임을 정의하고, 해당 클레임을 이용하여 토큰을 생성한다, 생성된 토큰에는 발급 시간과 만료 시간이 설정
 * validateToken: 주어진 토큰의 유효성을 검증, 토큰의 유효성은 토큰의 만료 여부와 주어진 UserDetails 객체의 유효성을 확인하여 결정
 * 다양한 보조 메서드들을 통해 토큰에서 사용자 이름, 만료 시간 등의 정보를 추출 가능
 */

@Component
public class JwtTokenUtil {
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.tokenExpirationTime}") private Integer tokenExpirationTime;
    @Value("${jwt.secret}") private String secret;

    // extract username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // extract expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // extract any information from token with secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // check token expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
