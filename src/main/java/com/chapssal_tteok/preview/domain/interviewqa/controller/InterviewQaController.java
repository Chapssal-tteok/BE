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

    @Operation(summary = "AI 면접 질문 생성", description = "자기소개서와 회사/직무 정보를 바탕으로 AI가 면접 질문을 생성합니다.")
    @PostMapping("/generate")
    public ApiResponse<InterviewQaResponseDTO.CreateInterviewQaResultDTO> generateInterviewQuestion(
            @PathVariable("interview_id") Long interviewId,
            @RequestBody @Valid InterviewQaRequestDTO.GenerateQuestionDTO request) {

        InterviewQa interviewQa = interviewQaCommandService.generateInterviewQuestion(interviewId, request);

        return ApiResponse.onSuccess(InterviewQaConverter.toCreateInterviewQaResultDTO(interviewQa));
    }

    @Operation(summary = "AI 추가 면접 질문 생성", description = "기존 문답을 기반으로 follow-up 질문을 AI가 생성하고 저장합니다.")
    @PostMapping("/follow-up")
    public ApiResponse<InterviewQaResponseDTO.CreateInterviewQaResultDTO> generateFollowUp(
            @PathVariable("interview_id") Long interviewId,
            @RequestBody @Valid InterviewQaRequestDTO.GenerateFollowUpDTO request) {

        InterviewQa followUpQa = interviewQaCommandService.generateFollowUpQuestion(interviewId, request);

        return ApiResponse.onSuccess(InterviewQaConverter.toCreateInterviewQaResultDTO(followUpQa));
    }

    @Operation(summary = "AI 면접 답변 분석", description = "질문, 답변, 자기소개서를 기반으로 AI가 분석 결과를 반환합니다.")
    @PostMapping("/{qa_id}/analyze")
    public ApiResponse<InterviewQaResponseDTO.InterviewQaDTO> analyzeAnswer(
            @PathVariable Long interview_id,
            @PathVariable Long qa_id,
            @RequestBody @Valid InterviewQaRequestDTO.AnalyzeAnswerDTO request) {

        InterviewQa updatedQa = interviewQaCommandService.analyzeAnswer(interview_id, qa_id, request);

        return ApiResponse.onSuccess(InterviewQaConverter.toInterviewQaDTO(updatedQa));
    }

    @Operation(summary = "면접 질문 수정", description = "면접 문답 ID를 통해 질문을 수정하고, 답변과 분석 내용을 초기화합니다.")
    @PatchMapping("/{qa_id}/question")
    public ApiResponse<InterviewQaResponseDTO.InterviewQaDTO> updateQuestion(
            @PathVariable Long interview_id,
            @PathVariable Long qa_id,
            @RequestBody @Valid InterviewQaRequestDTO.UpdateQuestionDTO request) {

        InterviewQa interviewQa = interviewQaCommandService.updateQuestion(interview_id, qa_id, request);

        return ApiResponse.onSuccess(InterviewQaConverter.toInterviewQaDTO(interviewQa));
    }

    @Operation(summary = "면접 답변 수정", description = "면접 문답 ID를 통해 답변을 수정하고, 분석 내용을 초기화합니다.")
    @PatchMapping("/{qa_id}/answer")
    public ApiResponse<InterviewQaResponseDTO.InterviewQaDTO> updateAnswer(
            @PathVariable Long interview_id,
            @PathVariable Long qa_id,
            @RequestBody @Valid InterviewQaRequestDTO.UpdateAnswerDTO request) {

        InterviewQa interviewQa = interviewQaCommandService.updateAnswer(interview_id, qa_id, request);

        return ApiResponse.onSuccess(InterviewQaConverter.toInterviewQaDTO(interviewQa));
    }

    @Operation(summary = "면접 분석 수정", description = "면접 문답 ID를 통해 분석 내용을 수정합니다.")
    @PatchMapping("/{qa_id}/analysis")
    public ApiResponse<InterviewQaResponseDTO.InterviewQaDTO> updateAnalysis(
            @PathVariable Long interview_id,
            @PathVariable Long qa_id,
            @RequestBody @Valid InterviewQaRequestDTO.UpdateAnalysisDTO request) {

        InterviewQa interviewQa = interviewQaCommandService.updateAnalysis(interview_id, qa_id, request);

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
