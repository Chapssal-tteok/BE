package com.chapssal_tteok.preview.domain.interview.converter;

import com.chapssal_tteok.preview.domain.interview.dto.InterviewRequestDTO;
import com.chapssal_tteok.preview.domain.interview.dto.InterviewResponseDTO;
import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaResponseDTO;
import com.chapssal_tteok.preview.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class InterviewConverter {

    public static InterviewResponseDTO.CreateInterviewResultDTO toCreateInterviewResultDTO(Interview interview) {

        return InterviewResponseDTO.CreateInterviewResultDTO.builder()
                .interviewId(interview.getId())
                .username(interview.getUser().getUsername())
                .title(interview.getTitle())
                .company(interview.getCompany())
                .position(interview.getPosition())
                .createdAt(interview.getCreatedAt())
                .build();
    }

    public static InterviewResponseDTO.InterviewDTO toInterviewDTO(Interview interview) {

        List<InterviewQaResponseDTO.InterviewQaDTO> interviewQaDTOs = interview.getInterviewQas().stream()
                .map(qa -> InterviewQaResponseDTO.InterviewQaDTO.builder()
                        .interviewQaId(qa.getId())
                        .interviewId(qa.getInterview().getId())
                        .number(qa.getNumber())
                        .question(qa.getQuestion())
                        .questionAudio(qa.getQuestionAudio())
                        .answer(qa.getAnswer())
                        .answerAudio(qa.getAnswerAudio())
                        .analysis(qa.getAnalysis())
                        .createdAt(qa.getCreatedAt())
                        .updatedAt(qa.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());

        return InterviewResponseDTO.InterviewDTO.builder()
                .interviewId(interview.getId())
                .username(interview.getUser().getUsername())
                .title(interview.getTitle())
                .company(interview.getCompany())
                .position(interview.getPosition())
                .interviewQas(interviewQaDTOs)
                .createdAt(interview.getCreatedAt())
                .updatedAt(interview.getUpdatedAt())
                .build();
    }

    public static InterviewResponseDTO.SimpleInterviewDTO toSimpleInterviewDTO(Interview interview) {

        return InterviewResponseDTO.SimpleInterviewDTO.builder()
                .interviewId(interview.getId())
                .username(interview.getUser().getUsername())
                .title(interview.getTitle())
                .company(interview.getCompany())
                .position(interview.getPosition())
                .createdAt(interview.getCreatedAt())
                .updatedAt(interview.getUpdatedAt())
                .build();
    }

    public static Interview toInterview(InterviewRequestDTO.CreateInterviewDTO request, User user) {

        return Interview.builder()
                .user(user)
                .title(request.getTitle())
                .company(request.getCompany())
                .position(request.getPosition())
                .build();
    }
}
