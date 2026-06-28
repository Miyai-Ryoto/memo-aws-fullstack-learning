package com.example.backend.service.validation.task;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.service.validation.AbstractValidationHandler;
import org.springframework.stereotype.Component;

@Component
public class TaskTagValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (request.getTags() == null || !request.getTags().contains("task")) {
            throw new IllegalArgumentException("タスクメモには task タグが必要です");
        }
    }
}