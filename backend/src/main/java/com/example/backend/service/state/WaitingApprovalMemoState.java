package com.example.backend.service.state;

import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

@Component
public class WaitingApprovalMemoState implements MemoState {

    @Override
    public void update(Memo memo, String title, String content, String tags) {
        throw new IllegalStateException("承認待ちメモは更新できません");
    }

    @Override
    public void delete(Memo memo) {
        throw new IllegalStateException("承認待ちメモは削除できません");
    }

    @Override
    public void publish(Memo memo) {
        throw new IllegalStateException("承認待ちメモはまだ公開できません");
    }

    @Override
    public void archive(Memo memo) {
        throw new IllegalStateException("承認待ちメモはアーカイブできません");
    }

    @Override
    public void restore(Memo memo) {
        throw new IllegalStateException("承認待ちメモは復元できません");
    }
}