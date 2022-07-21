package com.example.demo.revoke_access_token;

import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class RevokeAccessTokenServiceImpl implements RevokeAccessTokenService {

    private final RevokeAccessTokenRepository revokeAccessTokenRepository;

    public RevokeAccessTokenServiceImpl(RevokeAccessTokenRepository revokeAccessTokenRepository) {
        this.revokeAccessTokenRepository = revokeAccessTokenRepository;
    }

    @Override
    public void create(String accessToken, UUID userId) {
        RevokeAccessTokenEntity model = new RevokeAccessTokenEntity(accessToken,userId);
        model.setCreatedDate(System.currentTimeMillis());
       revokeAccessTokenRepository.save(model);
    }

    @Override
    public boolean isRevoked(String token) {
        return revokeAccessTokenRepository.existsByAccessToken(token);
    }
}
