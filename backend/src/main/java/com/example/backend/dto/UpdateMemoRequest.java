package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateMemoRequest {
    @NotBlank(message = "title is required")
    private String title;
    private String content;
    private String tags;

    public UpdateMemoRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getTags() { return tags; }
}
