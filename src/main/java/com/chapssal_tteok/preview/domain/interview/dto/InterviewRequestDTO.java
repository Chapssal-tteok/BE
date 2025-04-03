package com.chapssal_tteok.preview.domain.interview.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class InterviewRequestDTO {

    @Getter
    public static class CreateInterviewDTO {

        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;
    }

    @Getter
    public static class UpdateInterviewDTO {

        private String title;
    }
}
