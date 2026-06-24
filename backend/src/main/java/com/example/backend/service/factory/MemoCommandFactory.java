package com.example.backend.service.factory;

import com.example.backend.dto.MemoResponse;
import com.example.backend.repository.MemoHistoryRepository;
import com.example.backend.repository.MemoRepository;
import com.example.backend.service.command.MemoCommand;
import com.example.backend.service.command.UpdateMemoCommand;
import com.example.backend.service.command.UndoMemoCommand;
import com.example.backend.service.state.MemoStateResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoCommandFactory {

    private final MemoRepository memoRepository;
    private final MemoHistoryRepository memoHistoryRepository;
    private final MemoStateResolver memoStateResolver;

    public MemoCommand<MemoResponse> createUpdateCommand(
            Long id,
            String title,
            String content,
            String tags
    ) {
        return new UpdateMemoCommand(
                id,
                title,
                content,
                tags,
                memoRepository,
                memoHistoryRepository,
                memoStateResolver
        );
    }
    
    public MemoCommand<MemoResponse> createUndoCommand(Long id) {
    return new UndoMemoCommand(
            id,
            memoRepository,
            memoHistoryRepository
    );
}
}