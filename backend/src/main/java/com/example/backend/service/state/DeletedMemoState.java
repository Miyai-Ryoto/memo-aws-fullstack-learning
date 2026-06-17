package com.example.backend.service.state;

import com.example.backend.dto.request.MemoStatus;
import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

@Component
public class DeletedMemoState implements MemoState {

    @Override
    public void update(Memo memo, String title, String content, String tags) {
        throw new IllegalStateException("削除済みメモは更新できません");
    }

    @Override
    public void delete(Memo memo) {
        throw new IllegalStateException("すでに削除済みです");
    }

    @Override
    public void publish(Memo memo) {
        throw new IllegalStateException("削除済みメモは公開できません");
    }

    @Override
    public void archive(Memo memo) {
        throw new IllegalStateException("削除済みメモはアーカイブできません");
    }

    @Override
    public void restore(Memo memo) {
        // 削除済みは下書きに戻す
        memo.update(
                MemoStatus.DRAFT,
                memo.getTitle(),
                memo.getContent(),
                memo.getTags()
        );
    }
}