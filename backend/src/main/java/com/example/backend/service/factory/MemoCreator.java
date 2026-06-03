package com.example.backend.service.factory;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;

public abstract class MemoCreator {

    public final Memo create(CreateMemoRequest request) {
        beforeCreate(request);

        Memo memo = createMemo(request);

        afterCreate(memo);

        return memo;
    }

    public abstract MemoType getType();

    protected abstract Memo createMemo(CreateMemoRequest request);

    protected void beforeCreate(CreateMemoRequest request) {
        // 共通の前処理が必要になったらここに書く
    }

    protected void afterCreate(Memo memo) {
        // 共通の後処理が必要になったらここに書く
    }
}