package com.skr.ai.firstproject.service.impl;

import com.skr.ai.firstproject.service.AdvisorsService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdvisorsServiceImpl implements AdvisorsService {

    private ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userResource;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    public AdvisorsServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String resWithAdvisors(String query) {
        var response = this.chatClient.prompt()
               // .advisors(new SimpleLoggerAdvisor())
                .system(systemMessage)
                .user(user -> user.text(userResource)
                        .param("concept", query)
                )
                .call()
                .content();

        System.out.println(response);

        return response;
    }
}
