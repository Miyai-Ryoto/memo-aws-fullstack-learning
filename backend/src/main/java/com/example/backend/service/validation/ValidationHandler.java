package com.example.backend.service.validation;

import com.example.backend.dto.CreateMemoRequest;

public interface ValidationHandler {

    void setNext(ValidationHandler next);

    void validate(CreateMemoRequest request);
}