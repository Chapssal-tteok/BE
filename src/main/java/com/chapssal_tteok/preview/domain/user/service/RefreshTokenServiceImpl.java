package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.entity.RefreshToken;
import com.chapssal_tteok.preview.domain.user.repository.RefreshTokenRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken saveRefreshToken(String username, String refreshToken) {
        RefreshToken token = RefreshToken.builder()
                .username(username)
                .refreshToken(refreshToken)
                .build();

        return refreshTokenRepository.save(token);
    }

    @Override
    public RefreshToken findRefreshTokenByUsername(String username) {

        return refreshTokenRepository.findByUsername(username)
                .orElseThrow(()-> new UserHandler(ErrorStatus.USER_NOT_FOUND));
    }

    @Override
    public void deleteRefreshToken(String username) {

        refreshTokenRepository.deleteByUsername(username);
    }
}
