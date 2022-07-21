package com.example.demo.revoke_access_token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RevokeAccessTokenRepository extends JpaRepository<RevokeAccessTokenEntity, String> {
    boolean existsByAccessToken(String token);
}
