package com.chapssal_tteok.preview.domain.user.controller;

import com.chapssal_tteok.preview.domain.resume.dto.ResumeRequestDTO;
import com.chapssal_tteok.preview.domain.user.converter.UserConverter;
import com.chapssal_tteok.preview.domain.user.dto.UserRequestDTO;
import com.chapssal_tteok.preview.domain.user.dto.UserResponseDTO;
import com.chapssal_tteok.preview.domain.user.entity.User;
import com.chapssal_tteok.preview.domain.user.service.UserCommandService;
import com.chapssal_tteok.preview.domain.user.service.UserQueryService;
import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Operation(summary = "사용자 아이디 존재 여부 확인", description = "입력한 사용자 아이디가 이미 존재하는지 확인합니다.")
    @GetMapping("/exist")
    public ApiResponse<UserResponseDTO.UserExistenceDTO> checkUserExistence(@RequestParam String username) {

        boolean exists = userQueryService.isUsernameExist(username);

        return ApiResponse.onSuccess(new UserResponseDTO.UserExistenceDTO(exists));
    }

    @Operation(summary = "사용자 정보 조회", description = "현재 로그인된 사용자의 정보를 조회합니다.")
    @GetMapping("/info")
    public ApiResponse<UserResponseDTO.UserInfoDTO> getUserInfo() {

        User user = userQueryService.getCurrentUser();

        return ApiResponse.onSuccess(UserConverter.toUserInfoDTO(user));
    }
}
