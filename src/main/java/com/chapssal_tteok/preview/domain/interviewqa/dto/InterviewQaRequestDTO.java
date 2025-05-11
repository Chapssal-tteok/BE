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
    public static class GenerateQuestionDTO {

        @NotBlank
        private String company;

        @NotBlank
        private String position;

        @NotBlank
        private String resumeContent;
    }

    @Getter
    public static class AnalyzeAnswerDTO {

        @NotBlank
        private String question;

        @NotBlank
        private String answer;

        @NotBlank
        private String resume;
    }

    @Getter
    public static class UpdateInterviewQaDTO {

        private String question;

        private String questionAudio;

        private String answer;

        private String answerAudio;

        private String analysis;
    }

    @Getter
    public static class UpdateQuestionDTO {

        @NotBlank
        private String question;

        private String questionAudio;
    }

    @Getter
    public static class UpdateAnswerDTO {

        @NotBlank
        private String answer;

        private String answerAudio;
    }

    @Getter
    public static class UpdateAnalysisDTO {

        @NotBlank
        private String analysis;
    }
}
