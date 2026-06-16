package com.example.backend.service.factory;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoStatus;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;

@Component
public class TaskMemoCreator extends MemoCreator {

    @Override
    public MemoType getType() {
        return MemoType.TASK;
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