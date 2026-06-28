package com.example.backend.service.validation;

import com.example.backend.dto.CreateMemoRequest;
import org.springframework.stereotype.Component;

@Component
public class TitleRequiredValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("タイトルは必須です");
        }
    }
}