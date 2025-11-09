package com.skr.ai.firstproject.controller;

import com.skr.ai.firstproject.service.AdvisorsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AdvisorsController {

    private final AdvisorsService advisorsService;

    public AdvisorsController(AdvisorsService advisorsService) {
        this.advisorsService = advisorsService;
    }

    @GetMapping("/advisor")
    public ResponseEntity<String> advisor(@RequestParam(value = "q", required = true) String q) {
        var result = advisorsService.resWithAdvisors(q);
        return ResponseEntity.ok(result);
    }
}
