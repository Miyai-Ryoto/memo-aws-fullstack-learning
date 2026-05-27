package com.example.backend.dto.request;

public enum MemoType {
    NORMAL,
    TASK,
    LINK;

    public static MemoType from(String value) {
        if (value == null || value.isBlank()) {
            return NORMAL;
        }

        try {
            return MemoType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("不明なメモ種類です: " + value);
        }
    }
}
