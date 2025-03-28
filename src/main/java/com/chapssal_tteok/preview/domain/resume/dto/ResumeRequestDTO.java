package com.chapssal_tteok.preview.domain.resume.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ResumeRequestDTO {

    @Getter
    public static class CreateResumeDTO {

        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;
    }

    @Getter
    public static class UpdateResumeDTO {

        private String title;
    }
}
