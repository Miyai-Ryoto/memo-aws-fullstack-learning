package com.example.backend.service.validation.link;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.service.validation.AbstractValidationHandler;
import org.springframework.stereotype.Component;

@Component
public class LinkUrlRequiredValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new IllegalArgumentException("リンクメモのURLは必須です");
        }
    }
}