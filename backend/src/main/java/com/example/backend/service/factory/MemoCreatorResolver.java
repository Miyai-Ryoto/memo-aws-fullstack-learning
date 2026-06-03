package com.example.backend.service.factory;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.backend.dto.request.MemoType;

@Component
public class MemoCreatorResolver {

    private final Map<MemoType, MemoCreator> creatorMap = new EnumMap<>(MemoType.class);

    public MemoCreatorResolver(List<MemoCreator> creators) {
        for (MemoCreator creator : creators) {
            creatorMap.put(creator.getType(), creator);
        }
    }

    public MemoCreator resolve(MemoType type) {
        MemoCreator creator = creatorMap.get(type);

        if (creator == null) {
            throw new IllegalArgumentException("未対応のメモ種類です: " + type);
        }

        return creator;
    }
}