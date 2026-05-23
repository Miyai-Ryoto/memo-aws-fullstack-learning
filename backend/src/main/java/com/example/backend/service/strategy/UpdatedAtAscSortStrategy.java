package com.example.backend.service.strategy;

import com.example.backend.dto.request.MemoSortType;
import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class UpdatedAtAscSortStrategy implements MemoSortStrategy {

    @Override
    public MemoSortType getSortType() {
        return MemoSortType.UPDATED_AT_ASC;
    }

    @Override
    public Comparator<Memo> getComparator() {
        return Comparator.comparing(Memo::getUpdatedAt);
    }
}