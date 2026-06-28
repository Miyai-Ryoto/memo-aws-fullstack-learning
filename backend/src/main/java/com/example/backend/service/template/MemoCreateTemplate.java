package com.example.backend.service.template;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.MemoResponse;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.mapper.MemoMapper;
import com.example.backend.repository.MemoRepository;

public abstract class MemoCreateTemplate {

    private final MemoRepository memoRepository;

    protected MemoCreateTemplate(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public final MemoResponse create(CreateMemoRequest request) {
        validateCommon(request);

        validateType(request);

        Memo memo = createMemo(request);

        beforeSave(memo, request);

        Memo saved = memoRepository.save(memo);

        afterSave(saved, request);

        return MemoMapper.toResponse(saved);
    }

    public abstract MemoType getType();

    private void validateCommon(CreateMemoRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("タイトルは必須です");
        }

        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new IllegalArgumentException("本文は必須です");
        }
    
        if (request.getTitle() != null && request.getTitle().length() > 100) {
            throw new IllegalArgumentException("タイトルは100文字以内で入力してください");
        }
    
        if (request.getContent() != null && request.getContent().length() > 1000) {
            throw new IllegalArgumentException("本文は1000文字以内で入力してください");
        }
    
        if (request.getTags() != null && request.getTags().split(",").length > 10) {
            throw new IllegalArgumentException("タグは10個以内にしてください");
        }
    
        if (request.getTitle() != null && request.getTitle().contains("禁止")) {
            throw new IllegalArgumentException("タイトルに禁止ワードが含まれています");
        }
    
        if (request.getContent() != null && request.getContent().contains("禁止")) {
            throw new IllegalArgumentException("本文に禁止ワードが含まれています");
        }
    }

    protected abstract void validateType(CreateMemoRequest request);

    protected abstract Memo createMemo(CreateMemoRequest request);

    protected void beforeSave(Memo memo, CreateMemoRequest request) {
        // 必要なメモ種類だけoverrideする
    }

    protected void afterSave(Memo memo, CreateMemoRequest request) {
        // 必要なメモ種類だけoverrideする
    }
}