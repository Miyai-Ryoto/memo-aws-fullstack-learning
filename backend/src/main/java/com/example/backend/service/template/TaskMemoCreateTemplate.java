package com.example.backend.service.template;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;

@Component
public class TaskMemoCreateTemplate extends MemoCreateTemplate {

    public TaskMemoCreateTemplate(MemoRepository memoRepository) {
        super(memoRepository);
    }

    public MemoType getType() {
        return MemoType.TASK;
    }

    @Override
    protected void validateType(CreateMemoRequest request) {
        if (request.getDueDate() == null || request.getDueDate().isBlank()) {
            throw new IllegalArgumentException("タスクメモには期限が必要です");
        }
    }

    @Override
    protected Memo createMemo(CreateMemoRequest request) {
        return new Memo(
                request.getTitle(),
                request.getContent(),
                request.getTags()
        );
    }

    @Override
    protected void beforeSave(Memo memo, CreateMemoRequest request) {
        // 将来的に期限情報をセットするならここ
        // memo.setDueDate(request.getDueDate());
    }
}