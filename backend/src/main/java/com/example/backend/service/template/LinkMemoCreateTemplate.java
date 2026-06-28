package com.example.backend.service.template;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoStatus;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;
import com.example.backend.service.validation.MemoCommonValidationChain;
import com.example.backend.service.validation.link.LinkMemoValidationChain;

@Component
public class LinkMemoCreateTemplate extends MemoCreateTemplate {

    private final LinkMemoValidationChain linkMemoValidationChain;

    public LinkMemoCreateTemplate(MemoRepository memoRepository, MemoCommonValidationChain memoCommonValidationChain, LinkMemoValidationChain linkMemoValidationChain) {
        super(memoRepository, memoCommonValidationChain);
        this.linkMemoValidationChain = linkMemoValidationChain;
    }

    public MemoType getType() {
        return MemoType.LINK;
    }

    @Override
    protected void validateType(CreateMemoRequest request) {
        linkMemoValidationChain.build().validate(request);
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