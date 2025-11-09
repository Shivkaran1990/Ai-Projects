package com.skr.ai.firstproject.config;

import com.skr.ai.firstproject.advisor.CustomTokenCountAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder)
    {
        return builder.
                defaultAdvisors( new CustomTokenCountAdvisor(),
                     //   new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(List.of("sex","porn")))
                .defaultOptions(OpenAiChatOptions.builder().maxTokens(200).build())
                .build();
    }


}
