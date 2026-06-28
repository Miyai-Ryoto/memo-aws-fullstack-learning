package com.example.backend.service.validation.task;

import com.example.backend.service.validation.ValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMemoValidationChain {

    private final TaskContentRequiredValidator taskContentRequiredValidator;
    private final TaskDueDateValidator taskDueDateValidator;
    private final TaskTagValidator taskTagValidator;

    public ValidationHandler build() {
        taskContentRequiredValidator.setNext(taskDueDateValidator);
        taskDueDateValidator.setNext(taskTagValidator);

        return taskContentRequiredValidator;
    }
}