package com.example.backend.service;

import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;
import com.example.backend.exception.MemoNotFoundException;
import com.example.backend.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // 全件データ取得
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

    // ID指定にてデータを取得
    public MemoResponse findResponseById(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
    
        return new MemoResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent(),
                splitTags(memo.getTags()),
                memo.getUpdatedAt()
        );
    }

    // 新規登録
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

    // 削除
    @Transactional
    public void deleteMemo(Long id) {
        // 存在チェックして、無ければ 404 にしたいので例外
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
    
        memoRepository.delete(memo);
    }

    // 更新
    @Transactional
    public MemoResponse update(Long id, String title, String content, String tags) {
    
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
    
        memo.update(title, content, tags);
    
        Memo saved = memoRepository.save(memo);
    
        return new MemoResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                splitTags(saved.getTags()),
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
