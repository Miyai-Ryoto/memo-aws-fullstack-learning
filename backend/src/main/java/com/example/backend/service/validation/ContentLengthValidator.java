package com.example.backend.service.validation;

import com.example.backend.dto.CreateMemoRequest;
import org.springframework.stereotype.Component;

@Component
public class ContentLengthValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getContent() != null && request.getContent().length() > 1000) {
            throw new IllegalArgumentException("本文は1000文字以内で入力してください");
        }
    }
}