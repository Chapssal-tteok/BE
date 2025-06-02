package com.chapssal_tteok.preview.domain.interview.dto;

import com.chapssal_tteok.preview.domain.interviewqa.dto.InterviewQaResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class InterviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateInterviewResultDTO {

        private Long interviewId;
        private String username;
        private Long resumeId;
        private String title;
        private String company;
        private String position;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InterviewDTO {

        private Long interviewId;
        private String username;
        private Long resumeId;
        private String title;
        private String company;
        private String position;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime updatedAt;
    }
}
