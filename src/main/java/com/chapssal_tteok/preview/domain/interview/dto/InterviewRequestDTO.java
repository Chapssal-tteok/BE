package com.chapssal_tteok.preview.domain.interview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class InterviewRequestDTO {

    @Getter
    public static class CreateInterviewDTO {

        @Schema(description = "자기소개서 ID", example = "3")
        @NotNull(message = "자기소개서 ID는 필수 입력 값입니다.")
        private Long resumeId;

        @Schema(description = "면접 제목", example = "카카오 백엔드 면접 준비")
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;

        @Schema(description = "지원 회사명", example = "카카오")
        @NotBlank(message = "기업명은 필수 입력 값입니다.")
        private String company;

        @Schema(description = "지원 직무", example = "백엔드 개발자")
        @NotBlank(message = "지원 분야는 필수 입력 값입니다.")
        private String position;
    }

    @Getter
    public static class UpdateInterviewDTO {

        @Schema(description = "수정할 면접 제목", example = "라인 백엔드 면접 준비")
        private String title;

        @Schema(description = "수정할 지원 회사명", example = "라인")
        private String company;

        @Schema(description = "수정할 지원 직무", example = "백엔드 개발자")
        private String position;
    }
}
