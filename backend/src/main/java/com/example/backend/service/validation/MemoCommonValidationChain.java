package com.example.backend.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoCommonValidationChain {

    private final TitleRequiredValidator titleRequiredValidator;
    private final ContentRequiredValidator contentRequiredValidator;
    private final ContentLengthValidator contentLengthValidator;
    private final TagCountValidator tagCountValidator;
    private final NgWordValidator ngWordValidator;

    public ValidationHandler build() {
        titleRequiredValidator.setNext(contentRequiredValidator);
        contentRequiredValidator.setNext(contentLengthValidator);
        contentLengthValidator.setNext(tagCountValidator);
        tagCountValidator.setNext(ngWordValidator);

        return titleRequiredValidator;
    }
}