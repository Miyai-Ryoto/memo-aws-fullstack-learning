package com.example.backend.service.strategy;

import org.springframework.stereotype.Component;
import com.example.backend.dto.CreateMemoRequest;

@Component
public class TaskMemoStrategy implements MemoTypeStrategy {

    @Override
    public String getType() {
        return "task";
    }

    @Override
    public void validate(CreateMemoRequest req) {
        if (req.getDueDate() == null || req.getDueDate().isBlank()) {
            throw new IllegalArgumentException("タスクメモには期限が必要です");
        }
    }
}