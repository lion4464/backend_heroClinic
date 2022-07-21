package com.example.demo.configuration;

import com.example.demo.exceptions.TokenExpiredException;
import com.example.demo.revoke_access_token.RevokeAccessTokenService;
import com.example.demo.revoke_refresh_token.RevokeRefreshTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JWTokenUtil implements TokenUtil{

    private final RevokeRefreshTokenService revokeRefreshTokenService;
    private final RevokeAccessTokenService revokeAccessTokenService;


    @Value("${app.jjwt.secret}")
    private String secret;

    @Value("${app.jjwt.access_expiration}")
    private String accessExpirationTime;

    @Value("${app.jjwt.refresh_expiration}")
    private String refreshExpirationTime;

    public JWTokenUtil(RevokeRefreshTokenService revokeRefreshTokenService, RevokeAccessTokenService revokeAccessTokenService) {
        this.revokeRefreshTokenService = revokeRefreshTokenService;
        this.revokeAccessTokenService = revokeAccessTokenService;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public String generateAccessToken(UserDetailsImpl userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        return doGenerateToken(claims, userDetails.getId().toString(), accessExpirationTime);
    }

    @Override
    public String generateRefreshToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        return doGenerateToken(claims, userDetails.getId().toString(), refreshExpirationTime);

    }


    @Override
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration;
        try {
            expiration = getExpirationDateFromToken(token);
        } catch (TokenExpiredException e) {
            return true;
        }
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) throws TokenExpiredException {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) throws TokenExpiredException {
        Claims claims;
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token expired");
        }
        return claims;
    }



    @Override
    public Claims getClaims(String token) {
        Claims claims;
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    @Override
    public void revokeTokens(String accessToken, String refreshToken) {
        if(!isTokenExpired(accessToken)){
            Claims claims = getClaims(accessToken);
            UUID id = (UUID) claims.get("id");
            revokeAccessTokenService.create(accessToken, id);

        }
        if (!isTokenExpired(refreshToken)){
            Claims claims = getClaims(refreshToken);
            UUID id = (UUID) claims.get("id");
            revokeRefreshTokenService.create(refreshToken,id);
        }
    }


    private String doGenerateToken(Map<String, Object> claims, String userId, String accessExpirationTime) {
        long expirationTimeLong = Long.parseLong(accessExpirationTime);
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }
}



