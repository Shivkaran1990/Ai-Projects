package com.skr.ai.firstproject.service.impl;

import com.skr.ai.firstproject.service.PromptAndTemplateService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

@Service
public class PromptAndTemplateServiceImpl implements PromptAndTemplateService {
    ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userResource;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    PromptAndTemplateServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String prompt(String s) {
        String sysPrompt = "As an expert in programing and coding. Always write program in java. Now reply for this question:{query}";
        var response = chatClient
                .prompt()
                .user(promptUserSpec -> promptUserSpec.text(sysPrompt).param("query", s))
                .call()
                .content();
        System.out.println(response);
        return response;
    }

    @Override
    public String template(String s) {

//        var systemPromptTemplate= SystemPromptTemplate.builder().template("You are a helpful codding assistant. You are an expert in coding.").build();
//        var systemMessage=systemPromptTemplate.createMessage();
//
//        PromptTemplate userpromptTemplate=PromptTemplate.builder().template("what is {techName}? tell me {exampleName}").build();
//        var renderedMsg=  userpromptTemplate.createMessage(Map.of("techName","Java",
//              "exampleName","Collection"));
//
//        Prompt prompt=new Prompt(renderedMsg,systemMessage);
//        var response=this.chatClient.prompt(prompt).call().content();
//        System.out.println(response);

//        var response = this.chatClient.prompt()
//                .system("You are a helpful codding assistant. You are an expert in coding.")
//                .user(user -> user.text("what is {techName}? tell me {exampleName}")
//                        .param("techName", "Java")
//                        .param("exampleName", "Collection")
//                )
//                .call()
//                .content();


// best way is below

        var response = this.chatClient.prompt()
                .system(systemMessage)
                .user(user -> user.text(userResource)
                        .param("concept", "Python iteration")
                )
                .call()
                .content();

        System.out.println(response);

        return response;

    }
}
