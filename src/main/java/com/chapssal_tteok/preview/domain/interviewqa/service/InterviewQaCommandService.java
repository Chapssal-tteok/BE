package com.chapssal_tteok.preview.domain.interviewqa.service;

import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaRequestDTO;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;

public interface InterviewQaCommandService {

    InterviewQa createInterviewQa(Long interviewId, InterviewQaRequestDTO.CreateInterviewQaDTO request);

    InterviewQa generateInterviewQuestion(Long interviewId, InterviewQaRequestDTO.GenerateQuestionDTO request);

    InterviewQa generateFollowUpQuestion(Long interviewId, InterviewQaRequestDTO.GenerateFollowUpDTO request);

    InterviewQa analyzeAnswer(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.AnalyzeAnswerDTO request);

    InterviewQa updateQuestion(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateQuestionDTO request);

    InterviewQa updateAnswer(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateAnswerDTO request);

    InterviewQa updateAnalysis(Long interviewId, Long interviewQaId, InterviewQaRequestDTO.UpdateAnalysisDTO request);

    void deleteInterviewQa(Long interviewId, Long interviewQaId);
}
