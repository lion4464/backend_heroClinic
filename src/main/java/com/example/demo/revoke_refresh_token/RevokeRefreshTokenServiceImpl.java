package com.example.demo.revoke_refresh_token;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class RevokeRefreshTokenServiceImpl implements RevokeRefreshTokenService {

    private final RevokeRefreshTokenRepository revokeRefreshTokenRepository;

    public RevokeRefreshTokenServiceImpl(RevokeRefreshTokenRepository revokeRefreshTokenRepository) {
        this.revokeRefreshTokenRepository = revokeRefreshTokenRepository;
    }

    @Override
    public void create(String refreshToken, UUID userId) {
        RevokeRefreshTokenEntity model = new RevokeRefreshTokenEntity(refreshToken,userId);
        model.setCreatedDate(System.currentTimeMillis());
        revokeRefreshTokenRepository.save(model);
    }

    @Override
    public boolean isRevoked(String token) {
        return revokeRefreshTokenRepository.existsByRefreshToken(token);
    }
}
