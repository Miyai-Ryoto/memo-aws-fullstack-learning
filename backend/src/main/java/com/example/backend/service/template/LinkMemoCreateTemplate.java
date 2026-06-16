package com.example.backend.service.template;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoStatus;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;

@Component
public class LinkMemoCreateTemplate extends MemoCreateTemplate {

    public LinkMemoCreateTemplate(MemoRepository memoRepository) {
        super(memoRepository);
    }

    public MemoType getType() {
        return MemoType.LINK;
    }

    @Override
    protected void validateType(CreateMemoRequest request) {
        if (request.getUrl() == null || request.getUrl().isBlank()) {
            throw new IllegalArgumentException("リンクメモにはURLが必要です");
        }

        if (!request.getUrl().startsWith("http://") && !request.getUrl().startsWith("https://")) {
            throw new IllegalArgumentException("URLの形式が不正です");
        }
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

    @Override
    protected void beforeSave(Memo memo, CreateMemoRequest request) {
        // 将来的にURL情報をセットするならここ
        // memo.setUrl(request.getUrl());
    }
}