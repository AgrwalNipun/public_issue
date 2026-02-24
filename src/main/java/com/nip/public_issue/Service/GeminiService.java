package com.nip.public_issue.Service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.Part;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GeminiService {

    private final Client client = new Client();
    



    public String categorizeImage(MultipartFile imageFile) {

        try {

            byte[] imageBytes = imageFile.getBytes();

            String prompt = """
                    Analyze this image and categorize the public issue.
                    Return ONLY one word from:
                    General, Utilities, Infrastructure, Sanitation.
                    """;

            Content content = Content.builder()
                    .parts(List.of(
                            Part.fromText(prompt),
                            Part.fromBytes(imageBytes, imageFile.getContentType())
                    ))
                    .build();

            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-2.5-flash-lite",
                            List.of(content),
                            null
                    );

            return response.text().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "General";
        }
    }
}
