package com.example.backend.service.state;

import com.example.backend.entity.Memo;

public interface MemoState {

    void update(Memo memo, String title, String content, String tags);

    void delete(Memo memo);

    void publish(Memo memo);

    void archive(Memo memo);

    void restore(Memo memo);
}
