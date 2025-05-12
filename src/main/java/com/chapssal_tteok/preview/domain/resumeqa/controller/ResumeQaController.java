package com.chapssal_tteok.preview.domain.resumeqa.controller;

import com.chapssal_tteok.preview.domain.resumeqa.converter.ResumeQaConverter;
import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaRequestDTO;
import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaResponseDTO;
import com.chapssal_tteok.preview.domain.resumeqa.entity.ResumeQa;
import com.chapssal_tteok.preview.domain.resumeqa.service.ResumeQaCommandService;
import com.chapssal_tteok.preview.domain.resumeqa.service.ResumeQaQueryService;
import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/resumes/{resume_id}/qas")
@RequiredArgsConstructor
public class ResumeQaController {

    private final ResumeQaCommandService resumeQaCommandService;
    private final ResumeQaQueryService resumeQaQueryService;

    @Operation(summary = "자기소개서의 모든 문답 조회", description = "해당 자기소개서 ID에 속한 모든 문답을 순서대로 반환합니다.")
    @GetMapping
    public ApiResponse<List<ResumeQaResponseDTO.ResumeQaDTO>> getResumeQasByResumeId(
            @PathVariable("resume_id") Long resumeId) {

        List<ResumeQa> qas = resumeQaQueryService.getOrderedQasByResumeId(resumeId);

        List<ResumeQaResponseDTO.ResumeQaDTO> response = qas.stream()
                .map(ResumeQaConverter::toResumeQaDTO)
                .toList();

        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "자기소개서 문항 및 답변 생성", description = "새로운 자기소개서 문항 및 답변을 생성합니다.")
    @PostMapping
    public ApiResponse<ResumeQaResponseDTO.CreateResumeQaResultDTO> createResumeQa(
            @PathVariable Long resume_id,
            @RequestBody @Valid ResumeQaRequestDTO.CreateResumeQaDTO request) {

        ResumeQa resumeQa = resumeQaCommandService.createResumeQa(resume_id, request);

        return ApiResponse.onSuccess(ResumeQaConverter.toCreateResumeQaResultDTO(resumeQa));
    }

    @Operation(summary = "자기소개서 문항 및 답변 수정", description = "자기소개서 문답 ID를 통해 특정 자기소개서의 문항 및 답변을 수정합니다.")
    @PatchMapping("/{qa_id}")
    public ApiResponse<ResumeQaResponseDTO.ResumeQaDTO> updateResumeQa(
            @PathVariable Long resume_id,
            @PathVariable Long qa_id,
            @RequestBody @Valid ResumeQaRequestDTO.UpdateResumeQaDTO request) {

        ResumeQa resumeQa = resumeQaCommandService.updateResumeQa(resume_id, qa_id, request);

        return ApiResponse.onSuccess(ResumeQaConverter.toResumeQaDTO(resumeQa));
    }

    @Operation(summary = "자기소개서 문항 및 답변 분석", description = "자기소개서 문항 및 답변을 기반으로 AI가 분석 결과를 반환합니다.")
    @PostMapping("/{qa_id}/analyze")
    public ApiResponse<ResumeQaResponseDTO.ResumeQaDTO> analyzeResumeQa(
            @PathVariable Long resume_id,
            @PathVariable Long qa_id,
            @RequestBody @Valid ResumeQaRequestDTO.AnalyzeResumeQaDTO request) {

        ResumeQa resumeQa = resumeQaCommandService.analyzeResumeQa(resume_id, qa_id, request);

        return ApiResponse.onSuccess(ResumeQaConverter.toResumeQaDTO(resumeQa));
    }

    @Operation(summary = "자기소개서 문항 및 답변 삭제", description = "자기소개서 문답 ID를 통해 특정 자기소개서의 문항 및 답변을 삭제합니다.")
    @DeleteMapping("/{qa_id}")
    public ApiResponse<String> deleteResumeQa(
            @PathVariable Long resume_id,
            @PathVariable Long qa_id) {

        resumeQaCommandService.deleteResumeQa(resume_id, qa_id);

        return ApiResponse.onSuccess("Deleted Resume Q&A with resume qa ID: " + qa_id);
    }
}
