package com.example.backend.service.template;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.backend.dto.request.MemoType;

@Component
public class MemoCreateTemplateResolver {

    private final Map<MemoType, MemoCreateTemplate> templateMap = new EnumMap<>(MemoType.class);

    public MemoCreateTemplateResolver(List<MemoCreateTemplate> templates) {
        for (MemoCreateTemplate template : templates) {
            templateMap.put(template.getType(), template);
        }
    }

    public MemoCreateTemplate resolve(MemoType type) {
        MemoCreateTemplate template = templateMap.get(type);

        if (template == null) {
            throw new IllegalArgumentException("未対応のメモ種類です: " + type);
        }

        return template;
    }
}