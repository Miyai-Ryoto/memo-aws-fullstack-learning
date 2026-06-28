package com.example.backend.service.template;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoStatus;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;
import com.example.backend.service.validation.MemoCommonValidationChain;

@Component
public class NormalMemoCreateTemplate extends MemoCreateTemplate {

    public NormalMemoCreateTemplate(MemoRepository memoRepository, MemoCommonValidationChain memoCommonValidationChain) {
        super(memoRepository, memoCommonValidationChain);
    }

    public MemoType getType() {
        return MemoType.NORMAL;
    }

    @Override
    protected void validateType(CreateMemoRequest request) {
        // 通常メモ固有のチェックはなし
    }

    @Override
    protected Memo createMemo(CreateMemoRequest request) {
        return new Memo(
                MemoStatus.DRAFT,
                request.getTitle(),
                request.getContent(),
                request.getTags()
        );
    }
}