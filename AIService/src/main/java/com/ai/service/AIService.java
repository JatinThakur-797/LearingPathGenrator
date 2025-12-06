package com.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private final ChatClient chatClient;
    public AIService(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }
    public String generateContent(String promt){
        return chatClient.prompt().user(promt).call().content();
    }

}
