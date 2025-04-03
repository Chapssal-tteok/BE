package com.chapssal_tteok.preview.domain.interviewqa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class InterviewQaResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateInterviewQaResultDTO {

        private Long interviewQaId;
        private Long interviewId;
        private Long number;
        private String question;
        private String questionAudio;
        private String answer;
        private String answerAudio;
        private String analysis;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InterviewQaDTO {

        private Long interviewQaId;
        private Long interviewId;
        private Long number;
        private String question;
        private String questionAudio;
        private String answer;
        private String answerAudio;
        private String analysis;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime updatedAt;
    }
}
