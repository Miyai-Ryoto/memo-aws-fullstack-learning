package com.example.backend.service.validation.link;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.service.validation.AbstractValidationHandler;
import org.springframework.stereotype.Component;

@Component
public class LinkLocalhostValidator extends AbstractValidationHandler {

    @Override
    protected void doValidate(CreateMemoRequest request) {
        String content = request.getContent();

        if (content.contains("localhost")
                || content.contains("127.0.0.1")) {
            throw new IllegalArgumentException("localhost または 127.0.0.1 のURLは登録できません");
        }
    }
}