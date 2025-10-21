package com.skr.ai.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    ChatClient openAiChatClient;
    ChatClient ollamaChatClient;

    private ChatController(ChatClient openAiChatClient, ChatClient ollamaChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }
//    private ChatController(OllamaChatModel ollamaChatModel, OpenAiChatModel openAiChatModel){
//        this.openAiChatClient=ChatClient.builder(openAiChatModel).build();
//        this.ollamaChatClient=ChatClient.builder(ollamaChatModel).build();
//    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String q) {
        var response = ollamaChatClient.prompt(q).call().content();

        response+="---------------------------------------------";
        var response1 = openAiChatClient.prompt(q).call().content();
        System.out.println(response+response1);
        return ResponseEntity.ok(response+response1);
    }

}
