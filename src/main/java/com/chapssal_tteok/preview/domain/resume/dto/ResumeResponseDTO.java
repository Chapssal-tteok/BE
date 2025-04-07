package com.chapssal_tteok.preview.domain.resume.dto;

import com.chapssal_tteok.preview.domain.resumeqa.dto.ResumeQaResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ResumeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResumeResultDTO {

        private Long resumeId;
        private String username;
        private String title;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResumeDTO {

        private Long resumeId;
        private String username;
        private String title;
        private List<ResumeQaResponseDTO.ResumeQaDTO> resumeQas;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResumeDTO {

        private Long resumeId;
        private String username;
        private String title;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
        private LocalDateTime updatedAt;
    }
}
