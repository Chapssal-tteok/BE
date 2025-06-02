package com.chapssal_tteok.preview.domain.interviewqa.dto;

import com.chapssal_tteok.preview.global.common.enums.VoiceMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class InterviewQaRequestDTO {

    @Getter
    public static class CreateInterviewQaDTO {

        @Schema(description = "질문 텍스트", example = "자기소개를 해주세요.")
        @NotBlank(message = "질문은 필수 입력 값입니다.")
        private String question;

        @Schema(description = "질문 음성 URL (선택)", example = "https://s3.../tts/question.mp3")
        private String questionAudio;

        @Schema(description = "답변 텍스트 (선택)", example = "저는 성실한 개발자입니다.")
        private String answer;

        @Schema(description = "답변 음성 URL (선택)", example = "https://s3.../tts/answer.mp3")
        private String answerAudio;

        @Schema(description = "AI 분석 결과 (선택)", example = "논리적인 흐름이 좋습니다.")
        private String analysis;
    }

    @Getter
    public static class GenerateQuestionDTO {

        @NotBlank
        @Schema(example = "NAVER", description = "회사명")
        private String company;

        @NotBlank
        @Schema(example = "백엔드 개발자", description = "직무명")
        private String position;

        @NotBlank
        @Schema(
                description = "자기소개서 전체 본문 텍스트",
                example = "저는 항상 문제 해결을 즐기는 백엔드 개발자입니다. 학교 프로젝트에서 팀 리더를 맡았고..."
        )
        private String resumeContent;

        @Schema(example = "VOICE", description = "음성 모드: TEXT or VOICE")
        private VoiceMode mode = VoiceMode.TEXT;  // 기본값 TEXT
    }

    @Getter
    public static class GenerateFollowUpDTO {

        @Schema(example = "이 직무에 지원한 이유는 무엇인가요?", description = "기존 질문")
        @NotBlank
        private String question;

        @Schema(example = "백엔드 개발에 관심이 많고, 경험이 있어서입니다.", description = "기존 답변")
        @NotBlank
        private String answer;

        @Schema(example = "VOICE", description = "음성 모드: TEXT or VOICE")
        private VoiceMode mode = VoiceMode.TEXT;
    }

    @Getter
    public static class AnalyzeAnswerDTO {

        @Schema(description = "분석할 질문", example = "본인의 강점은 무엇인가요?")
        @NotBlank
        private String question;

        @Schema(description = "사용자의 답변", example = "문제를 해결하는 데 집요합니다.")
        @NotBlank
        private String answer;

        @Schema(description = "자기소개서 전체 내용", example = "...")
        @NotBlank
        private String resume;
    }


    @Getter
    public static class UpdateInterviewQaDTO {

        @Schema(description = "질문 텍스트", example = "프로젝트에서 맡은 역할은?")
        private String question;

        @Schema(description = "질문 음성 URL", example = "https://s3.../tts/q.mp3")
        private String questionAudio;

        @Schema(description = "답변 텍스트", example = "백엔드 서버 구현을 맡았습니다.")
        private String answer;

        @Schema(description = "답변 음성 URL", example = "https://s3.../tts/a.mp3")
        private String answerAudio;

        @Schema(description = "AI 분석 내용", example = "도전적인 태도가 드러납니다.")
        private String analysis;
    }

    @Getter
    public static class UpdateQuestionDTO {

        @Schema(description = "수정할 질문 텍스트", example = "지원 동기를 말해주세요.")
        @NotBlank
        private String question;

        @Schema(description = "질문 음성 URL", example = "https://s3.../tts/q.mp3")
        private String questionAudio;
    }

    @Getter
    public static class UpdateAnswerDTO {

        @Schema(description = "수정할 답변 텍스트", example = "기술적으로 성장하고 싶어서 지원했습니다.")
        @NotBlank
        private String answer;

        @Schema(description = "답변 음성 URL", example = "https://s3.../tts/a.mp3")
        private String answerAudio;
    }

    @Getter
    public static class UpdateAnalysisDTO {

        @Schema(description = "수정할 분석 내용", example = "논리성은 좋으나 구체적인 경험 추가 필요")
        @NotBlank
        private String analysis;
    }
}
