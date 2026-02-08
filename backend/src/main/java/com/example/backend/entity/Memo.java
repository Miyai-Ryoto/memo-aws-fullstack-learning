package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "memos")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 500)
    private String tags;

    @Column(nullable = false)
    private Instant updatedAt;

    public Memo(String title, String content, String tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.updatedAt = Instant.now();
    }

    public void update(String title, String content, String tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
