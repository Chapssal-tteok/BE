package com.chapssal_tteok.preview.domain.voice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class VoiceRequestDTO {

    @Getter
    public static class TtsRequest {
        @Schema(description = "음성으로 변환할 텍스트", example = "자기소개를 해주세요")
        @NotBlank(message = "텍스트는 필수입니다.")
        private String text;
    }
}
