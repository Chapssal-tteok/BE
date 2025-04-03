package com.chapssal_tteok.preview.domain.interview.controller;

import com.chapssal_tteok.preview.domain.interview.converter.InterviewConverter;
import com.chapssal_tteok.preview.domain.interview.dto.InterviewRequestDTO;
import com.chapssal_tteok.preview.domain.interview.dto.InterviewResponseDTO;
import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interview.service.InterviewCommandService;
import com.chapssal_tteok.preview.domain.interview.service.InterviewQueryService;
import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewCommandService interviewCommandService;
    private final InterviewQueryService interviewQueryService;

    @Operation(summary = "면접 생성", description = "새로운 면접을 생성합니다.")
    @PostMapping
    public ApiResponse<InterviewResponseDTO.CreateInterviewResultDTO> createInterview(@RequestBody @Valid InterviewRequestDTO.CreateInterviewDTO request) {

        Interview interview = interviewCommandService.createInterview(request);

        return ApiResponse.onSuccess(InterviewConverter.toCreateInterviewResultDTO(interview));
    }

    @Operation(summary = "면접 조회", description = "면접 ID를 통해 특정 면접을 조회합니다.")
    @GetMapping("/{interview_id}")
    public ApiResponse<InterviewResponseDTO.InterviewDTO> getInterview(@PathVariable Long interview_id) {

        Interview interview = interviewQueryService.getInterviewById(interview_id);

        return ApiResponse.onSuccess(InterviewConverter.toInterviewDTO(interview));
    }

    @Operation(summary = "면접 수정", description = "면접 ID를 통해 특정 면접을 수정합니다.")
    @PatchMapping("/{interview_id}")
    public ApiResponse<InterviewResponseDTO.InterviewDTO> updateInterview(
            @PathVariable Long interview_id,
            @RequestBody @Valid InterviewRequestDTO.UpdateInterviewDTO request) {

        Interview interview = interviewCommandService.updateInterview(interview_id, request);

        return ApiResponse.onSuccess(InterviewConverter.toInterviewDTO(interview));
    }

    @Operation(summary = "면접 삭제", description = "면접 ID를 통해 특정 면접을 삭제합니다.")
    @DeleteMapping("/{interview_id}")
    public ApiResponse<String> deleteInterview(@PathVariable Long interview_id) {

        interviewCommandService.deleteInterview(interview_id);

        return ApiResponse.onSuccess("Deleted Interview with interview ID: " + interview_id);
    }
}
