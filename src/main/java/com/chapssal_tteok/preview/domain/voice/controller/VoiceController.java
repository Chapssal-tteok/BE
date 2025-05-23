package com.chapssal_tteok.preview.domain.voice.controller;

import com.chapssal_tteok.preview.domain.voice.dto.VoiceRequestDTO;
import com.chapssal_tteok.preview.global.apiPayload.ApiResponse;
import com.chapssal_tteok.preview.infra.google.GoogleSttService;
import com.chapssal_tteok.preview.infra.google.GoogleTtsService;
import com.chapssal_tteok.preview.infra.s3.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/voice")
@RequiredArgsConstructor
public class VoiceController {

    private final GoogleTtsService googleTtsService;
    private final GoogleSttService googleSttService;
    private final S3Service s3Service;

    @Operation(summary = "텍스트를 음성으로 변환 (TTS)", description = "입력된 텍스트를 한국어 음성(MP3)으로 변환하여 반환합니다.")
    @PostMapping("/tts")
    public ApiResponse<String> textToSpeech(@RequestBody VoiceRequestDTO.TtsRequest request) throws Exception {

        byte[] audioBytes = googleTtsService.synthesizeText(request.getText());

        String audioUrl = s3Service.upload(audioBytes, "output.mp3", "audio/mpeg", "tts");

        return ApiResponse.onSuccess(audioUrl);
    }

    @Operation(summary = "음성을 텍스트로 변환 (STT)", description = "업로드된 한국어 음성 파일을 텍스트로 변환합니다.")
    @PostMapping(value = "/stt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Map<String, String>> speechToText(@RequestParam("file") MultipartFile file) throws Exception {

        byte[] audioData = file.getBytes();

        String originalFileName = file.getOriginalFilename();
        String audioUrl = s3Service.upload(audioData, originalFileName, file.getContentType(), "stt");

        String transcript = googleSttService.recognizeSpeechFromMp3(audioData);

        return ApiResponse.onSuccess(Map.of(
                "transcript", transcript,
                "audioUrl", audioUrl
        ));
    }
}
