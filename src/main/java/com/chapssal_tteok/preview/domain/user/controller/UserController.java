package com.chapssal_tteok.preview.domain.user.controller;

import com.chapssal_tteok.preview.domain.interview.converter.InterviewConverter;
import com.chapssal_tteok.preview.domain.interview.dto.InterviewResponseDTO;
import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interview.service.InterviewQueryService;
import com.chapssal_tteok.preview.domain.resume.converter.ResumeConverter;
import com.chapssal_tteok.preview.domain.resume.dto.ResumeResponseDTO;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.domain.resume.service.ResumeQueryService;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final ResumeQueryService resumeQueryService;
    private final InterviewQueryService interviewQueryService;

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

    @Operation(summary = "사용자 정보 수정", description = "현재 로그인된 사용자의 정보를 수정합니다.")
    @PatchMapping("/info")
    public ApiResponse<UserResponseDTO.UserInfoDTO> updateUserInfo(@RequestBody @Valid UserRequestDTO.UpdateUserDTO request) {

        User user = userCommandService.updateUserInfo(request);

        return ApiResponse.onSuccess(UserConverter.toUserInfoDTO(user));
    }

    @Operation(summary = "현재 로그인한 사용자의 자기소개서 목록 조회", description = "현재 로그인한 사용자의 자기소개서 목록을 조회합니다.")
    @GetMapping("/resumes")
    public ApiResponse<List<ResumeResponseDTO.SimpleResumeDTO>> getMyResumes() {

        List<Resume> resumes = userQueryService.getMyResumes();

        List<ResumeResponseDTO.SimpleResumeDTO> response = resumes.stream()
                .map(ResumeConverter::toSimpleResumeDTO)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "현재 로그인한 사용자의 면접 목록 조회", description = "현재 로그인한 사용자의 면접 목록을 조회합니다.")
    @GetMapping("/interviews")
    public ApiResponse<List<InterviewResponseDTO.InterviewDTO>> getMyInterviews() {

        List<Interview> interviews = userQueryService.getMyInterviews();

        List<InterviewResponseDTO.InterviewDTO> response = interviews.stream()
                .map(InterviewConverter::toInterviewDTO)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(response);
    }
}
