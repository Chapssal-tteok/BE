package com.chapssal_tteok.preview.domain.user.controller;

import com.chapssal_tteok.preview.domain.user.dto.*;
import com.chapssal_tteok.preview.domain.user.entity.Role;
import com.chapssal_tteok.preview.domain.user.service.UserAuthService;
import com.chapssal_tteok.preview.domain.user.service.UserCommandService;
import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class UserAuthController {

    private final UserCommandService userCommandService;
    private final UserAuthService userAuthService;

    @Operation(summary = "회원 가입", description = "새로운 사용자를 등록합니다.")
    @PostMapping("/register")
    public ApiResponse<RegisterResponseDTO> register (@RequestBody @Valid UserRequestDTO.CreateUserDTO userRequestDto){
        RegisterResponseDTO responseDto = userCommandService.registerUser(userRequestDto);

        return ApiResponse.onSuccess(responseDto);
    }

    @Operation(summary = "로그인", description = "사용자가 로그인하여 JWT 토큰을 받습니다.")
    @PostMapping("/login")
    public ApiResponse<JwtResponse> login (@RequestBody @Valid LoginRequestDTO loginRequestDto) {
        JwtResponse jwtResponse = userAuthService.login(loginRequestDto);

        return ApiResponse.onSuccess(jwtResponse);
    }

    @Operation(summary = "리프레시 토큰을 이용한 토큰 갱신", description = "리프레시 토큰을 통해 새로운 엑세스 토큰을 발급받습니다.")
    @PostMapping("/refresh")
    public ApiResponse<JwtResponse> refresh(@RequestBody RefreshRequestDTO refreshRequestDTO) {
        String refreshToken = refreshRequestDTO.getRefreshToken();
        log.info("🔁 Refresh Token: {}", refreshToken);

        JwtResponse jwtResponse = userAuthService.refresh(refreshToken);

        log.info("🔁 Refreshed Token: new access token: {}", jwtResponse.getAccessToken());
        return ApiResponse.onSuccess(jwtResponse);
    }

    @Operation(summary = "사용자 권한 변경", description = "관리자만 사용 가능한 API로, 사용자의 권한을 변경합니다.")
    @PatchMapping("/{user_id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponseDTO.UserInfoDTO> changeRole(@PathVariable Long user_id, @RequestParam Role newRole) {
        UserResponseDTO.UserInfoDTO userInfoDTO = userAuthService.updateUserRole(user_id, newRole);

        return ApiResponse.onSuccess(userInfoDTO);
    }
}
