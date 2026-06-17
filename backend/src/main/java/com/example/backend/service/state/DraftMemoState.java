package com.example.backend.service.state;

import com.example.backend.dto.request.MemoStatus;
import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

@Component
public class DraftMemoState implements MemoState {

    @Override
    public void update(Memo memo, String title, String content, String tags) {
        // 下書きは全部更新可能
        memo.update(MemoStatus.DRAFT, title, content, tags);
    }

    @Override
    public void delete(Memo memo) {
        // 下書きは削除可能
        memo.update(MemoStatus.DELETED, memo.getTitle(), memo.getContent(), memo.getTags());
    }

    @Override
    public void publish(Memo memo) {
        // 下書きは公開可能
        memo.update(MemoStatus.PUBLISHED, memo.getTitle(), memo.getContent(), memo.getTags());
    }

    @Override
    public void archive(Memo memo) {
        // 下書きはアーカイブ可能
        memo.update(MemoStatus.ARCHIVED, memo.getTitle(), memo.getContent(), memo.getTags());
    }

    @Override
    public void restore(Memo memo) {
        throw new IllegalStateException("下書きメモは復元対象ではありません");
    }
}