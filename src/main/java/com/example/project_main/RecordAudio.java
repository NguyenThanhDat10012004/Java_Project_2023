package com.example.project_main;

import javax.sound.sampled.*;
import java.io.*;

public class RecordAudio {

    public static void main(String[] args) {
        final String outputFile = "generatedAudio.wav"; // Tên file âm thanh đầu ra
        final int sampleRate = 44100; // Tần số mẫu âm thanh
        final int durationInSeconds = 5; // Thời lượng âm thanh (giây)

        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, 2, 4, sampleRate, false);

        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Microphone not supported");
                System.exit(0);
            }

            TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(info);
            targetLine.open(format);
            System.out.println("Recording...");

            AudioInputStream audioInputStream = new AudioInputStream(targetLine);

            targetLine.start();

            // Ghi âm trong khoảng thời gian chỉ định
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(outputFile));
            System.out.println("Recording finished. File WAV đã được tạo: " + outputFile);

            // Dừng ghi âm và đóng kết nối
            targetLine.stop();
            targetLine.close();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
