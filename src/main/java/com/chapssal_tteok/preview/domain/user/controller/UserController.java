package com.chapssal_tteok.preview.domain.user.controller;

import com.chapssal_tteok.preview.domain.user.dto.UserResponseDTO;
import com.chapssal_tteok.preview.domain.user.service.UserQueryService;
import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserQueryService userQueryService;

    @Operation(summary = "사용자 아이디 존재 여부 확인", description = "입력한 사용자 아이디가 이미 존재하는지 확인합니다.")
    @GetMapping("/users/exist")
    public ApiResponse<UserResponseDTO.UserExistenceDTO> checkUserExistence(@RequestParam String username) {

        boolean exists = userQueryService.isUsernameExist(username);
        return ApiResponse.onSuccess(new UserResponseDTO.UserExistenceDTO(exists));
    }
}
