package com.example.demo.revoke_refresh_token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevokeRefreshTokenRepository extends JpaRepository<RevokeRefreshTokenEntity, String> {
    boolean existsByRefreshToken(String refreshToken);
}
