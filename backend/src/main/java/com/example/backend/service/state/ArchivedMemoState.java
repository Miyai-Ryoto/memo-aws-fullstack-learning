package com.example.backend.service.state;

import com.example.backend.dto.request.MemoStatus;
import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

@Component
public class ArchivedMemoState implements MemoState {

    @Override
    public void update(Memo memo, String title, String content, String tags) {
        throw new IllegalStateException("アーカイブ済みメモは更新できません");
    }

    @Override
    public void delete(Memo memo) {
        // アーカイブ済みは削除済みに変更
        memo.update(
                MemoStatus.DELETED,
                memo.getTitle(),
                memo.getContent(),
                memo.getTags()
        );
    }

    @Override
    public void publish(Memo memo) {
        throw new IllegalStateException("アーカイブ済みメモは公開できません");
    }

    @Override
    public void archive(Memo memo) {
        throw new IllegalStateException("すでにアーカイブ済みです");
    }

    @Override
    public void restore(Memo memo) {
        // アーカイブ済みは下書きに戻す
        memo.update(
                MemoStatus.DRAFT,
                memo.getTitle(),
                memo.getContent(),
                memo.getTags()
        );
    }
}