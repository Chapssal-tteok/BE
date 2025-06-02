package com.chapssal_tteok.preview.infra.google;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class GoogleTtsService {

    public byte[] synthesizeText(String text) throws Exception {

        // 인증 파일 불러오기
        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("google-key.json");

        if (credentialsStream == null) {
            throw new IllegalStateException("⚠️ google-key.json not found in classpath!");
        }

        // 인증 설정 적용
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(() -> GoogleCredentials.fromStream(credentialsStream))
                .build();

        // 인증된 클라이언트 생성
        try (TextToSpeechClient client = TextToSpeechClient.create(settings)) {

            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("ko-KR")
                    .setSsmlGender(SsmlVoiceGender.FEMALE)
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            SynthesizeSpeechResponse response = client.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();

            return audioContents.toByteArray();
        }
    }
}
