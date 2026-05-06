package com.example.backend.mapper;

import java.util.Arrays;
import java.util.List;

import com.example.backend.dto.MemoDetailResponse;
import com.example.backend.dto.MemoListResponce;
import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;

public class MemoMapper {

    public static MemoListResponce toListResponse(Memo memo) {
        return new MemoListResponce(
                memo.getId(),
                memo.getTitle(),
                splitTags(memo.getTags()),
                memo.getUpdatedAt()
        );
    }

    public static MemoDetailResponse toDetailResponse(Memo memo) {
        return new MemoDetailResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent(),
                splitTags(memo.getTags()),
                memo.getUpdatedAt()
        );
    }

    public static MemoResponse toResponse(Memo memo) {
        return new MemoResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent(),
                splitTags(memo.getTags()),
                memo.getUpdatedAt()
        );
    }

    private static List<String> splitTags(String tags) {
        if (tags == null || tags.isBlank()) {
            return List.of();
        }

        return Arrays.stream(tags.split(","))
                .map(String::trim)
                .filter(tag -> !tag.isBlank())
                .toList();
    }
    
}
