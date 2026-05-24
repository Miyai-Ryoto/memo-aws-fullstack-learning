package com.example.backend.service.strategy;

import com.example.backend.dto.CreateMemoRequest;
import org.springframework.stereotype.Component;

@Component
public class LinkMemoStrategy implements MemoTypeStrategy {

    @Override
    public String getType() {
        return "link";
    }

    @Override
    public void validate(CreateMemoRequest request) {
        if (request.getUrl() == null || request.getUrl().isBlank()) {
            throw new IllegalArgumentException("リンクメモにはURLが必要です");
        }

        if (!request.getUrl().startsWith("http://") && !request.getUrl().startsWith("https://")) {
            throw new IllegalArgumentException("URLは http:// または https:// で始まる必要があります");
        }
    }
}