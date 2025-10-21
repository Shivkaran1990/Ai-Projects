package com.skr.ai.firstproject.controller;

import com.skr.ai.firstproject.entity.Tutorial;
import com.skr.ai.firstproject.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {
        var result = chatService.chat(q);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/tutorial")
    public ResponseEntity<Tutorial> getTut(@RequestParam(value = "q", required = true) String q) {
        var result = chatService.getTutorial(q);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getTuts(@RequestParam(value = "q", required = true) String q) {
        var result = chatService.getListTutorial(q);
        return ResponseEntity.ok(result);
    }

}
