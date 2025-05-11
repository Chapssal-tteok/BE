package com.chapssal_tteok.preview.domain.resumeqa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ResumeQaResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResumeQaResultDTO {

        private Long resumeQaId;
        private Long resumeId;
        private Integer orderIndex;
        private String question;
        private String answer;
        private String analysis;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResumeQaDTO {

        private Long resumeQaId;
        private Long resumeId;
        private Integer orderIndex;
        private String question;
        private String answer;
        private String analysis;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime updatedAt;
    }
}
