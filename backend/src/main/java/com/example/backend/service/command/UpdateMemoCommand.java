package com.example.backend.service.command;

import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;
import com.example.backend.entity.MemoHistory;
import com.example.backend.exception.MemoNotFoundException;
import com.example.backend.mapper.MemoMapper;
import com.example.backend.repository.MemoHistoryRepository;
import com.example.backend.repository.MemoRepository;
import com.example.backend.service.state.MemoState;
import com.example.backend.service.state.MemoStateResolver;

public class UpdateMemoCommand implements MemoCommand<MemoResponse> {

    private final Long id;
    private final String title;
    private final String content;
    private final String tags;

    private final MemoRepository memoRepository;
    private final MemoHistoryRepository memoHistoryRepository;
    private final MemoStateResolver memoStateResolver;

    public UpdateMemoCommand(
            Long id,
            String title,
            String content,
            String tags,
            MemoRepository memoRepository,
            MemoHistoryRepository memoHistoryRepository,
            MemoStateResolver memoStateResolver
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.memoRepository = memoRepository;
        this.memoHistoryRepository = memoHistoryRepository;
        this.memoStateResolver = memoStateResolver;
    }

    @Override
    public MemoResponse execute() {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));

        MemoHistory history = MemoHistory.from(memo);
        memoHistoryRepository.save(history);

        MemoState memoState = memoStateResolver.resolve(memo.getStatus());
        memoState.update(memo, title, content, tags);

        Memo saved = memoRepository.save(memo);

        return MemoMapper.toResponse(saved);
    }
}