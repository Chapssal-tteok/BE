package com.chapssal_tteok.preview.domain.interview.converter;

import com.chapssal_tteok.preview.domain.interview.dto.InterviewRequestDTO;
import com.chapssal_tteok.preview.domain.interview.dto.InterviewResponseDTO;
import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.user.entity.User;

public class InterviewConverter {

    public static InterviewResponseDTO.CreateInterviewResultDTO toCreateInterviewResultDTO(Interview interview) {

        return InterviewResponseDTO.CreateInterviewResultDTO.builder()
                .interviewId(interview.getId())
                .userId(interview.getUser().getId())
                .title(interview.getTitle())
                .createdAt(interview.getCreatedAt())
                .build();
    }

    public static InterviewResponseDTO.InterviewDTO toInterviewDTO(Interview interview) {

        return InterviewResponseDTO.InterviewDTO.builder()
                .interviewId(interview.getId())
                .userId(interview.getUser().getId())
                .title(interview.getTitle())
                .createdAt(interview.getCreatedAt())
                .updatedAt(interview.getUpdatedAt())
                .build();
    }

    public static Interview toInterview(InterviewRequestDTO.CreateInterviewDTO request, User user) {

        return Interview.builder()
                .user(user)
                .title(request.getTitle())
                .build();
    }
}
