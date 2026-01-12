package com.example.backend.dto;

import java.time.Instant;

public class MemoResponse {
    private Long id;
    private String title;
    private String content;
    private String tags;
    private Instant updatedAt;

    public MemoResponse(Long id, String title, String content, String tags, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getTags() { return tags; }
    public Instant getUpdatedAt() { return updatedAt; }
}
