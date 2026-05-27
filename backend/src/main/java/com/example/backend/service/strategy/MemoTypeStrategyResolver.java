package com.example.backend.service.strategy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.backend.dto.request.MemoType;

@Component
public class MemoTypeStrategyResolver {

    private final Map<MemoType, MemoTypeStrategy> strategyMap;

    public MemoTypeStrategyResolver(List<MemoTypeStrategy> strategies) {
        this.strategyMap = new EnumMap<>(MemoType.class);

        for (MemoTypeStrategy strategy : strategies) {
            strategyMap.put(strategy.getType(), strategy);
        }
    }

    public MemoTypeStrategy resolve(MemoType type) {
        MemoType memoType = type == null ? MemoType.NORMAL : type;

        MemoTypeStrategy strategy = strategyMap.get(memoType);

        if (strategy == null) {
            throw new IllegalArgumentException("不明なメモ種類です: " + memoType);
        }

        return strategy;
    }
}