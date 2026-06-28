package com.example.backend.service.validation;

import com.example.backend.dto.CreateMemoRequest;

public abstract class AbstractValidationHandler implements ValidationHandler {

    private ValidationHandler next;

    @Override
    public void setNext(ValidationHandler next) {
        this.next = next;
    }

    @Override
    public final void validate(CreateMemoRequest request) {
        doValidate(request);

        if (next != null) {
            next.validate(request);
        }
    }

    protected abstract void doValidate(CreateMemoRequest request);
}