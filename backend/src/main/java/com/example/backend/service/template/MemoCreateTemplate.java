package com.example.backend.service.template;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.MemoResponse;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.mapper.MemoMapper;
import com.example.backend.repository.MemoRepository;
import com.example.backend.service.validation.MemoCommonValidationChain;

public abstract class MemoCreateTemplate {

    private final MemoRepository memoRepository;
    private final MemoCommonValidationChain memoCommonValidationChain;

    protected MemoCreateTemplate(MemoRepository memoRepository, MemoCommonValidationChain memoCommonValidationChain) {
        this.memoRepository = memoRepository;
        this.memoCommonValidationChain = memoCommonValidationChain;
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
        memoCommonValidationChain.build().validate(request);
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