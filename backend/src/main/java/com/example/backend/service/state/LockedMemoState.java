package com.example.backend.service.state;

import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

@Component
public class LockedMemoState implements MemoState {

    @Override
    public void update(Memo memo, String title, String content, String tags) {
        throw new IllegalStateException("ロック中メモは更新できません");
    }

    @Override
    public void delete(Memo memo) {
        throw new IllegalStateException("ロック中メモは削除できません");
    }

    @Override
    public void publish(Memo memo) {
        throw new IllegalStateException("ロック中メモは公開できません");
    }

    @Override
    public void archive(Memo memo) {
        throw new IllegalStateException("ロック中メモはアーカイブできません");
    }

    @Override
    public void restore(Memo memo) {
        throw new IllegalStateException("ロック中メモは復元できません");
    }
}