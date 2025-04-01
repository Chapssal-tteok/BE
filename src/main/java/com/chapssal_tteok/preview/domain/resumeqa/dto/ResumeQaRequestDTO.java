package com.chapssal_tteok.preview.domain.resumeqa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ResumeQaRequestDTO {

    @Getter
    public static class CreateResumeQaDTO {

        @NotBlank(message = "이력서 ID는 필수 입력 값입니다.")
        private Long resumeId;

        @NotBlank(message = "번호는 필수 입력 값입니다.")
        private Long number;

        @NotBlank(message = "질문은 필수 입력 값입니다.")
        private String question;

        @NotBlank(message = "답변은 필수 입력 값입니다.")
        private String answer;

        private String analysis;
    }

    @Getter
    public static class UpdateResumeQaDTO {

        private String question;

        private String answer;

        private String analysis;
    }
}
