package com.chapssal_tteok.preview.infra.google;

import com.chapssal_tteok.preview.infra.audio.AudioConverter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class GoogleSttService {

    /**
     * 외부에서 mp3 바이트 배열을 전달하면,
     * 1) wav로 변환하고
     * 2) Google STT로 분석하여 텍스트 반환
     */
    public String recognizeSpeechFromMp3(byte[] mp3Bytes) throws Exception {

        byte[] wavBytes = AudioConverter.convertMp3ToWav(mp3Bytes);

        return recognizeWav(wavBytes);
    }

    public String recognizeWav(byte[] audioData) throws Exception {

        // 인증 파일 불러오기
        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("google-key.json");

        if (credentialsStream == null) {
            throw new IllegalStateException("⚠️ google-key.json not found in classpath!");
        }

        // 인증 설정 적용
        SpeechSettings settings = SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> GoogleCredentials.fromStream(credentialsStream))
                .build();

        // 인증된 클라이언트 생성
        try (SpeechClient speechClient = SpeechClient.create(settings)) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16) // PCM 16bit WAV
                    .setLanguageCode("ko-KR")
                    .setSampleRateHertz(16000)
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioData))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            StringBuilder resultText = new StringBuilder();

            for (SpeechRecognitionResult result : response.getResultsList()) {
                resultText.append(result.getAlternatives(0).getTranscript());
            }

            return resultText.toString();
        }
    }
}
