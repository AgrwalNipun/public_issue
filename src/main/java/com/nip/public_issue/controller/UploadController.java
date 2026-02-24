package com.nip.public_issue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nip.public_issue.Service.CloudinaryService;
import com.nip.public_issue.Service.GeminiService;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Autowired
    private GeminiService geminiService;

    @PostMapping
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            String imageTag = cloudinaryService.uploadImage(file);


            String imageUrl = "https://res.cloudinary.com/dqbr5fypv/image/upload/" + imageTag;
            String category = geminiService.categorizeImage(file);

            

            System.out.println(imageUrl);
            return ResponseEntity.ok(Map.of("imageUrl", imageUrl, "category", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }
}
