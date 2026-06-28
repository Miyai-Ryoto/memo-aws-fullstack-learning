package com.example.backend.service.validation.task;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.service.validation.AbstractValidationHandler;
import org.springframework.stereotype.Component;

@Component
public class TaskDueDateValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        if (!request.getContent().contains("期限:")) {
            throw new IllegalArgumentException("タスクメモには期限を入力してください");
        }

        if (request.getContent().contains("期限:未定")) {
            throw new IllegalArgumentException("期限未定のタスクは登録できません");
        }
    }
}