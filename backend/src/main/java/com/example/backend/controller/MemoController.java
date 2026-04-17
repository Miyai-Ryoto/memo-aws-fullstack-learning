package com.example.backend.controller;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.MemoResponse;
import com.example.backend.dto.UpdateMemoRequest;
import com.example.backend.service.MemoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174",
    "http://memo-app-fronted.s3-website.us-east-2.amazonaws.com"
})
@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping
    public List<MemoResponse> getMemos() {
        return memoService.findAllResponses();
    }

    @GetMapping("/{id}")
    public MemoResponse getMemoById(@PathVariable Long id) {
        return memoService.findResponseById(id);
    }


    @PostMapping
    public MemoResponse createMemo(@Valid @RequestBody CreateMemoRequest req) {
        return memoService.create(req.getTitle(), req.getContent(), req.getTags());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public MemoResponse updateMemo(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemoRequest request
    ) {
        return memoService.update(
                id,
                request.getTitle(),
                request.getContent(),
                request.getTags()
        );
    }
}
