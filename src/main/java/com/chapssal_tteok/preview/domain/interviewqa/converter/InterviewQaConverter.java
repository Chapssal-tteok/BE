package com.chapssal_tteok.preview.domain.interviewqa.converter;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaRequestDTO;
import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaResponseDTO;
import com.chapssal_tteok.preview.domain.interviewqa.entity.InterviewQa;

public class InterviewQaConverter {

    public static InterviewQaResponseDTO.CreateInterviewQaResultDTO toCreateInterviewQaResultDTO(InterviewQa interviewQa) {

        return InterviewQaResponseDTO.CreateInterviewQaResultDTO.builder()
                .interviewQaId(interviewQa.getId())
                .interviewId(interviewQa.getInterview().getId())
                .orderIndex(interviewQa.getOrderIndex())
                .question(interviewQa.getQuestion())
                .questionAudio(interviewQa.getQuestionAudio())
                .answer(interviewQa.getAnswer())
                .answerAudio(interviewQa.getAnswerAudio())
                .analysis(interviewQa.getAnalysis())
                .createdAt(interviewQa.getCreatedAt())
                .build();
    }

    public static InterviewQaResponseDTO.InterviewQaDTO toInterviewQaDTO(InterviewQa interviewQa) {

        return InterviewQaResponseDTO.InterviewQaDTO.builder()
                .interviewQaId(interviewQa.getId())
                .interviewId(interviewQa.getInterview().getId())
                .orderIndex(interviewQa.getOrderIndex())
                .question(interviewQa.getQuestion())
                .questionAudio(interviewQa.getQuestionAudio())
                .answer(interviewQa.getAnswer())
                .answerAudio(interviewQa.getAnswerAudio())
                .analysis(interviewQa.getAnalysis())
                .createdAt(interviewQa.getCreatedAt())
                .updatedAt(interviewQa.getUpdatedAt())
                .build();
    }

    public static InterviewQa toInterviewQa(InterviewQaRequestDTO.CreateInterviewQaDTO request, Interview interview, Integer nextOrderIndex) {

        return InterviewQa.builder()
                .interview(interview)
                .orderIndex(nextOrderIndex)
                .question(request.getQuestion())
                .questionAudio(request.getQuestionAudio())
                .answer(request.getAnswer())
                .answerAudio(request.getAnswerAudio())
                .analysis(request.getAnalysis())
                .build();
    }
}
