package com.skr.ai.ollama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    ChatClient chatClient;

    ChatController(ChatClient.Builder builder)
    {
        this.chatClient=builder.build();
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam("q") String q)
    {
       var result= chatClient.prompt(q).call().content();
        return ResponseEntity.ok(result);
    }
}
