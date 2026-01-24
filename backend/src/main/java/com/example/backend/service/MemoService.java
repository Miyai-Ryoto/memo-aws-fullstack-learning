package com.example.backend.service;

import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // 既存：Entityが欲しい用途があるなら残してOK
    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    // 追加：API返却用（DTOで返す）
    public List<MemoResponse> findAllResponses() {
        return memoRepository.findAll().stream()
                .map(m -> new MemoResponse(
                        m.getId(),
                        m.getTitle(),
                        m.getContent(),
                        splitTags(m.getTags()),
                        m.getUpdatedAt()
                ))
                .toList();
    }

    // Day7追加：新規作成（POST /memos 用）
    public MemoResponse create(String title, String content, String tags) {
        Memo saved = memoRepository.save(new Memo(title, content, tags));
        return new MemoResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                splitTags(saved.getTags()),
                saved.getUpdatedAt()
        );
    }

    private List<String> splitTags(String tags) {
        if (tags == null || tags.isBlank()) return List.of();
        return Arrays.stream(tags.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

    public void seed() {
        if (memoRepository.count() == 0) {
            memoRepository.save(new Memo("はじめてのメモ", "Day6: GET /memos を作った", "day6,backend"));
            memoRepository.save(new Memo("次にやること", "POST /memos を作る", "todo,api"));
        }
    }
}
