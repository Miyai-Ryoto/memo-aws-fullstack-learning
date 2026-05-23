package com.example.backend.service.strategy;

import com.example.backend.dto.request.MemoSortType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class MemoSortStrategyResolver {

    private final Map<MemoSortType, MemoSortStrategy> strategyMap;

    public MemoSortStrategyResolver(List<MemoSortStrategy> strategies) {
        this.strategyMap = new EnumMap<>(MemoSortType.class);

        for (MemoSortStrategy strategy : strategies) {
            this.strategyMap.put(strategy.getSortType(), strategy);
        }
    }

    public Optional<MemoSortStrategy> resolve(MemoSortType sortType) {
        if (sortType == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(strategyMap.get(sortType));
    }
}