package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.converter.UserConverter;
import com.chapssal_tteok.preview.domain.user.dto.JwtResponse;
import com.chapssal_tteok.preview.domain.user.dto.LoginRequestDTO;
import com.chapssal_tteok.preview.domain.user.dto.UserResponseDTO;
import com.chapssal_tteok.preview.domain.user.entity.RefreshToken;
import com.chapssal_tteok.preview.domain.user.entity.Role;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.domain.user.repository.RefreshTokenRepository;
import com.chapssal_tteok.preview.domain.user.repository.UserRepository;
import com.chapssal_tteok.preview.global.apiPayload.code.status.ErrorStatus;
import com.chapssal_tteok.preview.global.apiPayload.exception.GeneralException;
import com.chapssal_tteok.preview.global.apiPayload.exception.handler.UserHandler;
import com.chapssal_tteok.preview.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    // login
    @Override
    public JwtResponse login(LoginRequestDTO loginRequestDto) {

        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_NOT_CORRESPOND);
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail(), user.getRole());

        refreshTokenRepository.save(new RefreshToken(user.getEmail(), refreshToken));

        return new JwtResponse(user.getUsername(),accessToken, refreshToken);
    }

    @Override
    public UserResponseDTO.UserInfoDTO updateUserRole (Long userId, Role newRole) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        user.updateRole(newRole);

        return UserConverter.toUserInfoDTO(user);
    }

    @Override
    public JwtResponse refresh(String refreshToken) {

        log.info("🔁 Refreshing Token");
        // Refresh Token 유효성 검사
        jwtTokenProvider.validateToken(refreshToken);
        Claims claims = getClaims(refreshToken);
        String email = claims.getSubject();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        RefreshToken savedToken = refreshTokenRepository.findByUsername(email)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        log.info("🔁 Refreshing Token -> savedToken: {}", savedToken.getRefreshToken());

        // 저장된 Refresh Token과 비교 (공백 제거)
        if (!savedToken.getRefreshToken().trim().equals(refreshToken.trim())) {
            log.warn("❌ Refresh Token Mismatch!");
            throw new GeneralException(ErrorStatus.INVALID_REFRESH_TOKEN);
        }

        Role role = user.getRole();

        // 새로운 Access Token, Refresh Token 생성
        String newAccessToken = jwtTokenProvider.createAccessToken(email, role);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(email, role);

        log.info("🔁 Refreshing Token -> newAccessToken : {}", newAccessToken);
        log.info("🔁 Refreshing Token -> newRefreshToken : {}", newRefreshToken);

        // Refresh Token 업데이트
        savedToken.updateRefreshToken(newRefreshToken);
        refreshTokenRepository.save(new RefreshToken(email, newRefreshToken));

        return new JwtResponse(user.getUsername(), newAccessToken, newRefreshToken);
    }

    // Get Claims from Token
    private Claims getClaims(String token) {
        return jwtTokenProvider.getClaims(token);
    }
}
