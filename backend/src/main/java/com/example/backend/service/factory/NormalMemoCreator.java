package com.example.backend.service.factory;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;

@Component
public class NormalMemoCreator extends MemoCreator {

    @Override
    public MemoType getType() {
        return MemoType.NORMAL;
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