package com.example.backend.service.validation.link;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.service.validation.AbstractValidationHandler;
import org.springframework.stereotype.Component;

@Component
public class LinkUrlFormatValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        String content = request.getContent();

        if (!content.startsWith("http://")
                && !content.startsWith("https://")) {
            throw new IllegalArgumentException("リンクメモには http:// または https:// から始まるURLを入力してください");
        }
    }
}