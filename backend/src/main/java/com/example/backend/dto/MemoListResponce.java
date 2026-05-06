package com.example.backend.dto;

import java.time.Instant;
import java.util.List;

public class MemoListResponce {
    
    private Long id;
    private String title;
    private List<String> tags;
    private Instant updatedAt;

    public MemoListResponce(Long id, String title, List<String> tags, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public List<String> getTags() { return tags; }
    public Instant getUpdatedAt() { return updatedAt; }
    
}
