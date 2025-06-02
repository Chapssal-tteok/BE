package com.chapssal_tteok.preview.domain.resume.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ResumeRequestDTO {

    @Getter
    public static class CreateResumeDTO {

        @Schema(description = "자기소개서 제목", example = "쿠팡 프론트 자기소개서")
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;

        @Schema(description = "지원 회사명", example = "쿠팡")
        @NotBlank(message = "기업명은 필수 입력 값입니다.")
        private String company;

        @Schema(description = "지원 직무", example = "프론트엔드 개발자")
        @NotBlank(message = "지원 분야는 필수 입력 값입니다.")
        private String position;
    }

    @Getter
    public static class UpdateResumeDTO {

        @Schema(description = "수정할 자기소개서 제목", example = "네이버 프론트 자기소개서")
        private String title;

        @Schema(description = "수정할 지원 회사명", example = "네이버")
        private String company;

        @Schema(description = "수정할 지원 직무", example = "프론트엔드 개발자")
        private String position;
    }
}
