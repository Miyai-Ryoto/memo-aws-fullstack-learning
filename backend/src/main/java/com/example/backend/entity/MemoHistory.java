package com.example.backend.entity;

import com.example.backend.dto.request.MemoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemoHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemoStatus status;

    private String title;

    @Column(length = 5000)
    private String content;

    private String tags;

    private LocalDateTime savedAt;

    public MemoHistory(Memo memo) {
        this.memoId = memo.getId();
        this.status = memo.getStatus();
        this.title = memo.getTitle();
        this.content = memo.getContent();
        this.tags = memo.getTags();
        this.savedAt = LocalDateTime.now();
    }

    public static MemoHistory from(Memo memo) {
        return new MemoHistory(memo);
    }
}