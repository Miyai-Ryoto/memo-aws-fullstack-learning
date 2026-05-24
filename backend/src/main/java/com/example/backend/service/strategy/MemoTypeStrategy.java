package com.example.backend.service.strategy;

import com.example.backend.dto.CreateMemoRequest;

public interface MemoTypeStrategy {
    String getType();
    void validate(CreateMemoRequest req);
}