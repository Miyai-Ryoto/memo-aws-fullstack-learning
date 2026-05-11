package com.example.backend.controller;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.MemoDetailResponse;
import com.example.backend.dto.MemoListResponce;
import com.example.backend.dto.MemoResponse;
import com.example.backend.dto.UpdateMemoRequest;
import com.example.backend.service.MemoService;
import com.example.backend.service.MemoSseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    private final MemoSseService memoSseService;

    @GetMapping
    public List<MemoListResponce> getMemos(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) LocalDate updatedFrom,
            @RequestParam(required = false) LocalDate updatedTo,
            @RequestParam(required = false) Boolean favoriteOnly,
            @RequestParam(required = false) Boolean archivedOnly,
            @RequestParam(required = false) String sort
    ) {
        if (title != null || tag != null || content != null || updatedFrom != null || updatedTo != null || favoriteOnly != null || archivedOnly != null || sort != null) {
            return memoService.searchResponses(title, tag, content, updatedFrom, updatedTo, favoriteOnly, archivedOnly, sort);
        }
        return memoService.findAllResponses();
    }

    @GetMapping("/{id}")
    public MemoDetailResponse getMemoById(@PathVariable Long id) {
        return memoService.findResponseById(id);
    }


    @PostMapping
    public MemoResponse createMemo(@Valid @RequestBody CreateMemoRequest req) {
        MemoResponse createdMemo = memoService.create(
            req.getTitle(), 
            req.getContent(), 
            req.getTags()
        );
        memoSseService.notifyMemoChanged();
        return createdMemo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);

        memoSseService.notifyMemoChanged();
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public MemoResponse updateMemo(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemoRequest req
    ) {
        MemoResponse updatedMemo = memoService.update(
                id,
                req.getTitle(),
                req.getContent(),
                req.getTags()
        );

        memoSseService.notifyMemoChanged();

        return updatedMemo;
    }
}
