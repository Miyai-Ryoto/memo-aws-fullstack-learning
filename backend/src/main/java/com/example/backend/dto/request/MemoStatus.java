package com.example.backend.dto.request;

public enum MemoStatus {
    DRAFT,          // 下書き
    PUBLISHED,      // 公開中
    ARCHIVED,       // アーカイブ済み
    DELETED,        // 削除済み
    LOCKED,         // ロック中
    WAITING_APPROVAL // 承認待ち
}
