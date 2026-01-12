package com.example.backend.controller;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;
import com.example.backend.service.MemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping
    public List<Memo> getMemos() {
        return memoService.findAll();
    }

    @PostMapping
    public MemoResponse createMemo(@Valid @RequestBody CreateMemoRequest req) {
    return memoService.create(req.getTitle(), req.getContent(), req.getTags());
}
}
