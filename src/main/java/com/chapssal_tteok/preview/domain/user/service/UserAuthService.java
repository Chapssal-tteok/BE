package com.chapssal_tteok.preview.domain.user.service;

import com.chapssal_tteok.preview.domain.user.dto.JwtResponse;
import com.chapssal_tteok.preview.domain.user.dto.LoginRequestDTO;
import com.chapssal_tteok.preview.domain.user.dto.UserResponseDTO;
import com.chapssal_tteok.preview.domain.user.entity.Role;

public interface UserAuthService {

    JwtResponse login(LoginRequestDTO loginRequestDto);

    UserResponseDTO.UserInfoDTO updateUserRole(Long userId, Role role);

    JwtResponse refresh(String refreshToken);
}
