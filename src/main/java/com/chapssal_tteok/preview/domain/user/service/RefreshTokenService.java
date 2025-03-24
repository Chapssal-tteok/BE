package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken saveRefreshToken(String username, String refreshToken);

    RefreshToken findRefreshTokenByUsername(String username);

    void deleteRefreshToken(String username);
}
