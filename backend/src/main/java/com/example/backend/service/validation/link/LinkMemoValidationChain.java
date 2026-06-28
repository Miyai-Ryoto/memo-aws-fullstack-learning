package com.example.backend.service.validation.link;

import com.example.backend.service.validation.ValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LinkMemoValidationChain {

    private final LinkUrlRequiredValidator linkUrlRequiredValidator;
    private final LinkUrlFormatValidator linkUrlFormatValidator;
    private final LinkLocalhostValidator linkLocalhostValidator;
    private final LinkTagValidator linkTagValidator;

    public ValidationHandler build() {
        linkUrlRequiredValidator.setNext(linkUrlFormatValidator);
        linkUrlFormatValidator.setNext(linkLocalhostValidator);
        linkLocalhostValidator.setNext(linkTagValidator);

        return linkUrlRequiredValidator;
    }
}