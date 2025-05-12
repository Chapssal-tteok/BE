package com.chapssal_tteok.preview.domain.voice.controller;

import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import com.chapssal_tteok.preview.infra.google.GoogleSttService;
import com.chapssal_tteok.preview.infra.google.GoogleTtsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/voice")
@RequiredArgsConstructor
public class VoiceController {

    private final GoogleTtsService googleTtsService;
    private final GoogleSttService googleSttService;

    @Operation(summary = "텍스트를 음성으로 변환 (TTS)", description = "입력된 텍스트를 한국어 음성(MP3)으로 변환하여 반환합니다.")
    @PostMapping("/tts")
    public ResponseEntity<byte[]> textToSpeech(@RequestBody Map<String, String> body) throws Exception {
        String text = body.get("text");
        byte[] audioBytes = googleTtsService.synthesizeText(text);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"output.mp3\"")
                .body(audioBytes);
    }

    @Operation(summary = "음성을 텍스트로 변환 (STT)", description = "업로드된 한국어 음성 파일을 텍스트로 변환합니다. (16kHz PCM WAV 권장)")
    @PostMapping(value = "/stt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> speechToText(@RequestParam("file") MultipartFile file) throws Exception {
        byte[] audioData = file.getBytes();
        String transcript = googleSttService.recognizeSpeech(audioData);
        return ApiResponse.onSuccess(transcript);
    }
}
