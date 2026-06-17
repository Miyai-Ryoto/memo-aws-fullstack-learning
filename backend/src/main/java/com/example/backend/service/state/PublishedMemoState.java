package com.example.backend.service.state;

import com.example.backend.dto.request.MemoStatus;
import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

@Component
public class PublishedMemoState implements MemoState {

    @Override
    public void update(Memo memo, String title, String content, String tags) {
        // 公開中はタイトル変更不可
        // 本文とタグだけ更新可能
        memo.update(
                MemoStatus.PUBLISHED,
                memo.getTitle(),
                content,
                tags
        );
    }

    @Override
    public void delete(Memo memo) {
        // 公開中は物理削除せず、削除済みにする
        memo.update(
                MemoStatus.DELETED,
                memo.getTitle(),
                memo.getContent(),
                memo.getTags()
        );
    }

    @Override
    public void publish(Memo memo) {
        throw new IllegalStateException("すでに公開中です");
    }

    @Override
    public void archive(Memo memo) {
        // 公開中はアーカイブ可能
        memo.update(
                MemoStatus.ARCHIVED,
                memo.getTitle(),
                memo.getContent(),
                memo.getTags()
        );
    }

    @Override
    public void restore(Memo memo) {
        throw new IllegalStateException("公開中メモは復元対象ではありません");
    }
}