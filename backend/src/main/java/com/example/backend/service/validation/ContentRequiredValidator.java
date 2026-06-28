package com.example.backend.service.validation;

import com.example.backend.dto.CreateMemoRequest;
import org.springframework.stereotype.Component;

@Component
public class ContentRequiredValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new IllegalArgumentException("本文は必須です");
        }
    }
}