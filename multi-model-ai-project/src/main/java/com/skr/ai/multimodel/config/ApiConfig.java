package com.skr.ai.multimodel.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean(name="openAiChatClient")
    public ChatClient getOpenAiChatClient(OpenAiChatModel openAiChatModel)
    {
       return ChatClient.builder(openAiChatModel).defaultOptions(OpenAiChatOptions.builder()
               .temperature(0.5)
               .model("gpt-4o-mini")
               .maxTokens(1000).build()
       ).build();
    }

    @Bean(name="ollamaChatClient")
    public ChatClient getOllamaChatClient(OllamaChatModel ollamaChatModel)
    {
     return ChatClient.builder(ollamaChatModel).defaultOptions(OllamaOptions.builder().temperature(0.7).build()).build();
    }
}
