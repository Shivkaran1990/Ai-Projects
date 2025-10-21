package com.skr.ai.firstproject.service;

import com.skr.ai.firstproject.entity.Tutorial;

import java.util.List;

public interface ChatService {
    String chat(String query);
    Tutorial getTutorial(String query);
    List<Tutorial> getListTutorial(String query);

}
