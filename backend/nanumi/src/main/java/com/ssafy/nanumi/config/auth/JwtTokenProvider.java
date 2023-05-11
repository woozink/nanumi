package com.ssafy.nanumi.config.auth;

import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@Getter
public class JwtTokenProvider {
    private final String key;

    public JwtTokenProvider( @Value("${jwt.secret}") String key) {
        this.key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public JwtToken generateToken(String userEmail){
        String authorities = "USER";

        String accessToken = Jwts.builder()
                .setSubject(userEmail)
                .claim("auth", authorities)
                .setExpiration(new Date(System.currentTimeMillis()+1000L*60*60))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis()+1000L*60*60*7))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken){
        Claims claims = parseClaims(accessToken);
        if(claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없는 토큰 입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

    }

    public boolean validateToken(String token){
        boolean flag = false;
        System.out.println("검증을 해보자ㅏ");

        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token);
            System.out.println("이게뭘가"+claims.getBody());
            flag = !claims.getBody().getExpiration().before(new Date());
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("Invalid JWT Token", e);
        }catch (ExpiredJwtException e){
            log.info("Expired JWT Token", e);
        }catch (IllegalArgumentException e){
            log.info("JWT claims string is empty.", e);
        }
        return flag;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
