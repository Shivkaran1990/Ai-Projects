package com.skr.ai.firstproject.controller;

import com.skr.ai.firstproject.entity.Tutorial;
import com.skr.ai.firstproject.service.ChatService;
import com.skr.ai.firstproject.service.impl.PromptAndTemplateServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PromptAndTemplateController {

    private final PromptAndTemplateServiceImpl promptAndTemplateServiceImpl;

    public PromptAndTemplateController(PromptAndTemplateServiceImpl promptAndTemplateServiceImpl) {
        this.promptAndTemplateServiceImpl = promptAndTemplateServiceImpl;
    }

    @GetMapping("/prompt")
    public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {
        var result = promptAndTemplateServiceImpl.prompt(q);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/template")
    public ResponseEntity<String> getTut(@RequestParam(value = "q", required = true) String q) {
        var result = promptAndTemplateServiceImpl.template(q);
        return ResponseEntity.ok(result);
    }


}
