package com.chapssal_tteok.preview.domain.interviewqa.controller;

import com.chapssal_tteok.preview.domain.interviewqa.service.InterviewQaCommandService;
import com.chapssal_tteok.preview.domain.interviewqa.converter.InterviewQaConverter;
import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaRequestDTO;
import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaResponseDTO;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;
import com.chapssal_tteok.preview.domain.interviewqa.service.InterviewQaQueryService;
import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/interviews/{interview_id}/qas")
@RequiredArgsConstructor
public class InterviewQaController {

    private final InterviewQaCommandService interviewQaCommandService;
    private final InterviewQaQueryService interviewQaQueryService;

    @Operation(summary = "면접 세트의 모든 문답 조회", description = "해당 면접 ID에 속한 모든 문답을 순서대로 반환합니다.")
    @GetMapping
    public ApiResponse<List<InterviewQaResponseDTO.InterviewQaDTO>> getInterviewQasByInterviewId(
            @PathVariable("interview_id") Long interviewId) {

        List<InterviewQa> qas = interviewQaQueryService.getOrderedQasByInterviewId(interviewId);

        List<InterviewQaResponseDTO.InterviewQaDTO> response = qas.stream()
                .map(InterviewQaConverter::toInterviewQaDTO)
                .toList();

        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "면접 질문 및 답변 생성", description = "새로운 면접 질문 및 답변을 생성합니다.")
    @PostMapping
    public ApiResponse<InterviewQaResponseDTO.CreateInterviewQaResultDTO> createInterviewQa(
            @PathVariable Long interview_id,
            @RequestBody @Valid InterviewQaRequestDTO.CreateInterviewQaDTO request) {

        InterviewQa interviewQa = interviewQaCommandService.createInterviewQa(interview_id, request);

        return ApiResponse.onSuccess(InterviewQaConverter.toCreateInterviewQaResultDTO(interviewQa));
    }

    @Operation(summary = "면접 질문 및 답변 수정", description = "면접 문답 ID를 통해 특정 면접 질문 및 답변을 수정합니다.")
    @PatchMapping("/{qa_id}")
    public ApiResponse<InterviewQaResponseDTO.InterviewQaDTO> updateInterviewQa(
            @PathVariable Long interview_id,
            @PathVariable Long qa_id,
            @RequestBody @Valid InterviewQaRequestDTO.UpdateInterviewQaDTO request) {

        InterviewQa interviewQa = interviewQaCommandService.updateInterviewQa(interview_id, qa_id, request);

        return ApiResponse.onSuccess(InterviewQaConverter.toInterviewQaDTO(interviewQa));
    }

    @Operation(summary = "면접 질문 및 답변 삭제", description = "면접 문답 ID를 통해 특정 면접 질문 및 답변을 삭제합니다.")
    @DeleteMapping("/{qa_id}")
    public ApiResponse<String> deleteInterviewQa(
            @PathVariable Long interview_id,
            @PathVariable Long qa_id) {

        interviewQaCommandService.deleteInterviewQa(interview_id, qa_id);

        return ApiResponse.onSuccess("Deleted Interview Q&A with interview qa ID: " + qa_id);
    }
}
