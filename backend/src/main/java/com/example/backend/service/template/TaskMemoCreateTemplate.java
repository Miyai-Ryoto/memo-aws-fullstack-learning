package com.example.backend.service.template;

import org.springframework.stereotype.Component;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.request.MemoStatus;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;
import com.example.backend.service.validation.MemoCommonValidationChain;
import com.example.backend.service.validation.task.TaskMemoValidationChain;

@Component
public class TaskMemoCreateTemplate extends MemoCreateTemplate {

    private final TaskMemoValidationChain taskMemoValidationChain;

    public TaskMemoCreateTemplate(MemoRepository memoRepository, MemoCommonValidationChain memoCommonValidationChain, TaskMemoValidationChain taskMemoValidationChain) {
        super(memoRepository, memoCommonValidationChain);
        this.taskMemoValidationChain = taskMemoValidationChain;
    }

    public MemoType getType() {
        return MemoType.TASK;
    }

    @Override
    protected void validateType(CreateMemoRequest request) {
        taskMemoValidationChain.build().validate(request);
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