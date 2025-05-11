package com.chapssal_tteok.preview.domain.resumeqa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ResumeQaRequestDTO {

    @Getter
    public static class CreateResumeQaDTO {

        @NotBlank(message = "질문은 필수 입력 값입니다.")
        private String question;

        private String answer;

        private String analysis;
    }

    @Getter
    public static class UpdateResumeQaDTO {

        private String question;

        private String answer;

        private String analysis;
    }

    @Getter
    public static class AnalyzeResumeQaDTO {

        @NotBlank
        private String resume;

        @NotBlank
        private String company;

        @NotBlank
        private String position;
    }
}
