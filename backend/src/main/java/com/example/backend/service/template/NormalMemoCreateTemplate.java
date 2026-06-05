package com.example.backend.service.template;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;

@Component
public class NormalMemoCreateTemplate extends MemoCreateTemplate {

    public NormalMemoCreateTemplate(MemoRepository memoRepository) {
        super(memoRepository);
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
                request.getTitle(),
                request.getContent(),
                request.getTags()
        );
    }
}