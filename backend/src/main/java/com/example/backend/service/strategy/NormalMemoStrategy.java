package com.example.backend.service.strategy;

import com.example.backend.dto.CreateMemoRequest;
import org.springframework.stereotype.Component;

@Component
public class NormalMemoStrategy implements MemoTypeStrategy {

    @Override
    public String getType() {
        return "normal";
    }

    @Override
    public void validate(CreateMemoRequest request) {
        // 通常メモは追加チェックなし
    }
}