package com.chapssal_tteok.preview.infra.audio;

import java.io.*;

public class AudioConverter {

    public static byte[] convertMp3ToWav(byte[] mp3Bytes) throws IOException, InterruptedException {
        // 1. mp3 파일 임시 저장
        File tempMp3 = File.createTempFile("audio", ".mp3");
        File tempWav = File.createTempFile("audio", ".wav");

        try (FileOutputStream fos = new FileOutputStream(tempMp3)) {
            fos.write(mp3Bytes);
        }

        // 2. ffmpeg 명령어 실행
        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg", "-y", // -y: overwrite
                "-i", tempMp3.getAbsolutePath(),
                "-ar", "16000",        // 샘플레이트
                "-ac", "1",            // 모노
                "-f", "wav",           // 출력 형식
                "-acodec", "pcm_s16le",
                tempWav.getAbsolutePath()
        );

        Process process = pb.start();
        if (process.waitFor() != 0) {
            throw new RuntimeException("FFmpeg 변환 실패");
        }

        // 3. 변환된 wav 파일을 byte[]로 반환
        return java.nio.file.Files.readAllBytes(tempWav.toPath());
    }
}
