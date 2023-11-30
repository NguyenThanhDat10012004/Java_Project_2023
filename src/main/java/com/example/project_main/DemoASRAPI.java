package com.example.project_main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DemoASRAPI {
    public static void main(String[] args) {
        String result = null;
        try {
            String filePath = "C:/Java_Project_2023/generatedAudio.wav"; // Đường dẫn đến file của bạn
            File file = new File(filePath);

            URL url = new URL("https://api.fpt.ai/hmi/asr/general");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("api-key", "Xp4dxyfPu6Fs3qPskmORRNeuuISGTnxP");
            connection.setDoOutput(true);

            FileInputStream fileInputStream = new FileInputStream(file);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                result = response.toString();
            } else {
                result = "POST request failed with error code: " + responseCode;
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
