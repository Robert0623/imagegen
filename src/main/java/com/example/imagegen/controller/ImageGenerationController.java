package com.example.imagegen.controller;

import com.example.imagegen.entity.ImageRequest;
import com.example.imagegen.service.ImageService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

@RestController
public class ImageGenerationController {

    private final ImageService imageService;

    public ImageGenerationController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/image", consumes = "application/json; charset=UTF-8")
    public List<String> image(@RequestBody ImageRequest request) throws IOException {
        ImageResponse imageResponse = imageService.getImageGen(request);
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
        return imageUrls;
    }
}
