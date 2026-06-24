package com.example.backend.service.command;

import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;
import com.example.backend.entity.MemoHistory;
import com.example.backend.exception.MemoNotFoundException;
import com.example.backend.mapper.MemoMapper;
import com.example.backend.repository.MemoHistoryRepository;
import com.example.backend.repository.MemoRepository;

public class UndoMemoCommand implements MemoCommand<MemoResponse> {

    private final Long id;

    private final MemoRepository memoRepository;
    private final MemoHistoryRepository memoHistoryRepository;

    public UndoMemoCommand(
            Long id,
            MemoRepository memoRepository,
            MemoHistoryRepository memoHistoryRepository
    ) {
        this.id = id;
        this.memoRepository = memoRepository;
        this.memoHistoryRepository = memoHistoryRepository;
    }

    @Override
    public MemoResponse execute() {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));

        MemoHistory history = memoHistoryRepository
                .findTopByMemoIdOrderBySavedAtDesc(id)
                .orElseThrow(() -> new IllegalStateException("履歴が見つかりませんでした。"));

        memo.restoreFrom(history);

        memoHistoryRepository.delete(history);

        Memo saved = memoRepository.save(memo);

        return MemoMapper.toResponse(saved);
    }
}