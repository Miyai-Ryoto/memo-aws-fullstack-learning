package com.example.backend.service.factory;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.service.strategy.MemoTypeStrategyResolver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemoFactory {

    private final MemoTypeStrategyResolver memoTypeStrategyResolver;

    public Memo create(CreateMemoRequest request) {
        MemoType memoType = MemoType.from(request.getType());

        memoTypeStrategyResolver
                .resolve(memoType)
                .validate(request);

        return new Memo(
                request.getTitle(),
                request.getContent(),
                request.getTags()
        );
    }
}