package com.example.backend.service.state;

import com.example.backend.dto.request.MemoStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoStateResolver {

    private final DraftMemoState draftMemoState;
    private final PublishedMemoState publishedMemoState;
    private final ArchivedMemoState archivedMemoState;
    private final DeletedMemoState deletedMemoState;
    private final LockedMemoState lockedMemoState;
    private final WaitingApprovalMemoState waitingApprovalMemoState;


    public MemoState resolve(MemoStatus status) {

        if (status == MemoStatus.DRAFT) {
            return draftMemoState;
        } else if (status == MemoStatus.PUBLISHED) {
            return publishedMemoState;
        } else if (status == MemoStatus.ARCHIVED) {
            return archivedMemoState;
        } else if (status == MemoStatus.DELETED) {
            return deletedMemoState;
        } else if (status == MemoStatus.LOCKED) {
            return lockedMemoState;
        } else if (status == MemoStatus.WAITING_APPROVAL) {
            return waitingApprovalMemoState;
        }

        throw new IllegalStateException("未対応のメモ状態です: " + status);
    }
}