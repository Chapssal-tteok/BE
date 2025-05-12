package com.chapssal_tteok.preview.infra.google;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class GoogleSttService {

    public String recognizeSpeech(byte[] audioData) throws Exception {
        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16) // PCM 16bit WAV
                    .setLanguageCode("ko-KR")
                    .setSampleRateHertz(16000)  // 녹음 샘플레이트 맞추기
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
