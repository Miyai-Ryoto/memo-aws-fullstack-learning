package com.example.backend.service.validation;

import com.example.backend.dto.CreateMemoRequest;
import org.springframework.stereotype.Component;

@Component
public class NgWordValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getTitle() != null && request.getTitle().contains("禁止")) {
            throw new IllegalArgumentException("タイトルに禁止ワードが含まれています");
        }

        if (request.getContent() != null && request.getContent().contains("禁止")) {
            throw new IllegalArgumentException("本文に禁止ワードが含まれています");
        }
    }
}