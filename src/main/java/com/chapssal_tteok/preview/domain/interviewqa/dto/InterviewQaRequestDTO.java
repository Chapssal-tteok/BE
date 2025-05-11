package com.chapssal_tteok.preview.domain.interviewqa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class InterviewQaRequestDTO {

    @Getter
    public static class CreateInterviewQaDTO {

        @NotBlank(message = "질문은 필수 입력 값입니다.")
        private String question;

        private String questionAudio;

        private String answer;

        private String answerAudio;

        private String analysis;
    }

    @Getter
    public static class UpdateInterviewQaDTO {

        private String question;

        private String questionAudio;

        private String answer;

        private String answerAudio;

        private String analysis;
    }
}
