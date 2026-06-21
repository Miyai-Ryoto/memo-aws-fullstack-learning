package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

import com.example.backend.dto.request.MemoStatus;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "memos")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemoStatus status = MemoStatus.DRAFT;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 500)
    private String tags;

    @Column(nullable = false)
    private Instant updatedAt;

    public Memo(MemoStatus status, String title, String content, String tags) {
        this.status = status;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.updatedAt = Instant.now();
    }

    public void update(MemoStatus status, String title, String content, String tags) {
        this.status = status;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }
    
    public void restoreFrom(MemoHistory history) {
        this.status = history.getStatus();
        this.title = history.getTitle();
        this.content = history.getContent();
        this.tags = history.getTags();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
