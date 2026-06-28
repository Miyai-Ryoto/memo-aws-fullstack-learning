package com.example.backend.service.validation;

import com.example.backend.dto.CreateMemoRequest;
import org.springframework.stereotype.Component;

@Component
public class TagCountValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getTags() != null && request.getTags().split(",").length > 10) {
            throw new IllegalArgumentException("タグは10個以内にしてください");
        }
    }
}