package com.example.backend.service.strategy;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoType;

public interface MemoTypeStrategy {
    MemoType getType();
    void validate(CreateMemoRequest req);
}