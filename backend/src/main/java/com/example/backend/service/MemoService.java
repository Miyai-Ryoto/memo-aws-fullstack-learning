package com.example.backend.service;

import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;
import com.example.backend.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    // Day7追加：新規作成（POST /memos 用）
    public MemoResponse create(String title, String content, String tags) {
        Memo saved = memoRepository.save(new Memo(title, content, tags));
        return new MemoResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getTags(),
                saved.getUpdatedAt()
        );
    }

    public void seed() {
        if (memoRepository.count() == 0) {
            memoRepository.save(new Memo("はじめてのメモ", "Day6: GET /memos を作った", "day6,backend"));
            memoRepository.save(new Memo("次にやること", "POST /memos を作る", "todo,api"));
        }
    }
}
