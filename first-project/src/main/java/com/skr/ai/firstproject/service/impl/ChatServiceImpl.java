package com.skr.ai.firstproject.service.impl;

import com.skr.ai.firstproject.entity.Tutorial;
import com.skr.ai.firstproject.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;
    public ChatServiceImpl(ChatClient.Builder builder)
    {
        this.chatClient=builder.build();
    }


    @Override
    public String chat(String query) {
//        String userPrompt="top 5 alternate definitions "+query;
//        String systemPrompt="As a expert in Java";
//        var response=chatClient.prompt()
//                .user(userPrompt)
//                .system(systemPrompt)
//                .call()
//                .content();

        Prompt prompt=new Prompt(query);
        var response=chatClient.prompt(prompt)
                .call()
                .chatResponse()
                        .getMetadata();
               // .content();

        System.out.println(response);

        return response.toString();
    }

    @Override
    public Tutorial getTutorial(String query) {
        Tutorial tutorial=chatClient.prompt(query)
                .call().entity(Tutorial.class);
        System.out.println(tutorial);
        return tutorial;
    }

    @Override
    public List<Tutorial> getListTutorial(String query) {
        List<Tutorial> tutorials=chatClient.prompt(query)
                .call().entity(new ParameterizedTypeReference<List<Tutorial>>() {
                });
        System.out.println(tutorials);
        return tutorials;
    }
}
