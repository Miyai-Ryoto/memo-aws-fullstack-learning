package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateMemoRequest {

    @NotBlank(message = "title is required")
    private String title;
    private String content;
    private String tags;

    public CreateMemoRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getTags() { return tags; }
}
