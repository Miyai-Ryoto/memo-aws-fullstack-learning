package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateMemoRequest {

    @NotBlank(message = "title is required")
    @Size(max = 100, message = "title must be at most 100 characters")
    private String title;
    @Size(max = 1000, message = "content must be at most 1000 characters")
    private String content;
    @Size(max = 100, message = "tags must be at most 100 characters")
    private String tags;

    private String Type;
    private String dueDate;
    private String url;

    public CreateMemoRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getTags() { return tags; }
    public String getType() { return Type; }
    public String getDueDate() { return dueDate; }
    public String getUrl() { return url; }
}
