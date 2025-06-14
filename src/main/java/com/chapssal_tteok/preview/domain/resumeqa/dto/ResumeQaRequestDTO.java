package com.chapssal_tteok.preview.domain.resumeqa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ResumeQaRequestDTO {

    @Getter
    public static class CreateResumeQaDTO {

        @Schema(description = "자기소개서 질문", example = "본인의 장점을 말해주세요.")
        @NotBlank(message = "질문은 필수 입력 값입니다.")
        private String question;

        @Schema(description = "자기소개서 답변 (선택)", example = "저는 항상 문제를 끝까지 해결하는 끈기가 있습니다.")
        private String answer;

        @Schema(description = "AI 분석 내용 (선택)", example = "경험과 성격 연결이 잘 되어 있음")
        private String analysis;
    }

    @Getter
    public static class UpdateResumeQaDTO {

        @Schema(description = "수정할 질문", example = "가장 기억에 남는 경험은 무엇인가요?")
        private String question;

        @Schema(description = "수정할 답변", example = "교환학생 당시 언어 장벽을 극복한 경험이 있습니다.")
        private String answer;

        @Schema(description = "수정할 분석 내용", example = "자기주도적인 태도가 드러남")
        private String analysis;
    }

    @Getter
    public static class AnalyzeResumeQaDTO {

        @Schema(description = "자기소개서 질문", example = "지원 동기와 입사 후 포부를 작성해주세요.")
        @NotBlank
        private String question;

        @Schema(description = "자기소개서 답변", example = "저는 적극적인 성격으로 팀 프로젝트에서...")
        @NotBlank
        private String answer;

        @Schema(description = "지원 회사명", example = "카카오")
        @NotBlank
        private String company;

        @Schema(description = "지원 직무명", example = "프론트엔드 개발자")
        @NotBlank
        private String position;
    }
}
