package com.example.backend.service.strategy;

import com.example.backend.entity.Memo;

import java.util.Comparator;

import com.example.backend.dto.request.MemoSortType;

public interface MemoSortStrategy {

    MemoSortType getSortType();

    Comparator<Memo> getComparator();
    
}
