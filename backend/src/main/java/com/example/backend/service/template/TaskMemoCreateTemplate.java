package com.example.backend.service.template;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoStatus;
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

        if (!request.getContent().contains("期限:")) {
            throw new IllegalArgumentException("タスクメモには期限を入力してください");
        }
    
        if (request.getContent().contains("期限:未定")) {
            throw new IllegalArgumentException("期限未定のタスクは登録できません");
        }
    
        if (request.getTags() == null || !request.getTags().contains("task")) {
            throw new IllegalArgumentException("タスクメモには task タグが必要です");
        }
    }

    @Override
    protected Memo createMemo(CreateMemoRequest request) {
        return new Memo(
                MemoStatus.DRAFT,
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