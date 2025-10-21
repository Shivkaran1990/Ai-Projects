package com.skr.ai.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Tutorial {
    String title;
    String content;
    Date createOn;
    String createdBy;
}
