package com.example.backend.service.validation.link;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.service.validation.AbstractValidationHandler;
import org.springframework.stereotype.Component;

@Component
public class LinkTagValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getTags() == null || !request.getTags().contains("link")) {
            throw new IllegalArgumentException("リンクメモには link タグが必要です");
        }
    }
}