package com.example.imagegen.service;

import com.example.imagegen.entity.ImageRequest;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse getImageGen(ImageRequest request) {
        ImageResponse imageResponse = openAiImageModel
                .call(new ImagePrompt(request.getMessage(),
                        OpenAiImageOptions.builder()
                                .model(request.getModel()) // 모델 설정
                                .quality("hd") // 이미지 품질
                                .withN(request.getN()) // 생성할 이미지 개수
                                .height(1024)
                                .width(1024)
                                .build())
                ); // 모델 설정
        return imageResponse;
    }
}
