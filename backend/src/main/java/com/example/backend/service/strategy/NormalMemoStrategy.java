package com.example.backend.service.strategy;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoType;

import org.springframework.stereotype.Component;

@Component
public class NormalMemoStrategy implements MemoTypeStrategy {

    @Override
    public MemoType getType() {
        return MemoType.NORMAL;
    }

    @Override
    public void validate(CreateMemoRequest request) {
        // 通常メモは追加チェックなし
    }
}