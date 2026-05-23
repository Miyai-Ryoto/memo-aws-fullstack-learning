package com.example.backend.service.strategy;

import com.example.backend.dto.request.MemoSortType;
import com.example.backend.entity.Memo;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class TitleDescSortStrategy implements MemoSortStrategy {

    @Override
    public MemoSortType getSortType() {
        return MemoSortType.TITLE_DESC;
    }

    @Override
    public Comparator<Memo> getComparator() {
        return Comparator.comparing(Memo::getTitle).reversed();
    }
}