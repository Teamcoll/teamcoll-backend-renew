package org.devs.teamcoll.Teamcoll.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.devs.teamcoll.Teamcoll.security.auth.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;

@Slf4j
@Component
public class JwtProvider  {
    final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateJwtToken(Authentication authentication) {

        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(authUser.getEmail())
                .setIssuedAt(new java.util.Date())
                .claim("authorities", authUser.getAuthorities())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))//sql.Date
                .signWith(secretKey)
                .compact();

    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
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
