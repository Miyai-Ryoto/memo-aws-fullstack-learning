package com.example.backend.controller;

import com.example.backend.service.MemoSseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174",
    "http://memo-app-fronted.s3-website.us-east-2.amazonaws.com"
})
@RestController
@RequiredArgsConstructor
public class MemoSseController {

    private final MemoSseService memoSseService;

    @GetMapping("/sse/memos")
    public SseEmitter connect() {
        return memoSseService.connect();
    }
}