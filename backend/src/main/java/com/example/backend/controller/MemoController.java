package com.example.backend.controller;

import com.example.backend.entity.Memo;
import com.example.backend.service.MemoService;
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
}
