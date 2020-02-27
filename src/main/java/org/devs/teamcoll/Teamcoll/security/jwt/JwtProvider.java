package org.devs.teamcoll.Teamcoll.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.devs.teamcoll.Teamcoll.security.service.UserPrinciple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private static Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateJwtToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        String token =  Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                //.setIssuedAt(new java.util.Date())
                //.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))//sql.Date
                .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(secretKey)
                .compact();
        log.info("TOKEN : {}", token);
        return token;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}
