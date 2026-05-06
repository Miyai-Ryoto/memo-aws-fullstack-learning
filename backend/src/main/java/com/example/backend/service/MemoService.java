package com.example.backend.service;

import com.example.backend.dto.MemoDetailResponse;
import com.example.backend.dto.MemoListResponce;
import com.example.backend.dto.MemoResponse;
import com.example.backend.entity.Memo;
import com.example.backend.exception.MemoNotFoundException;
import com.example.backend.mapper.MemoMapper;
import com.example.backend.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // 全件データ取得
    public List<MemoListResponce> findAllResponses() {
        return memoRepository.findAll().stream() // データが0件でも異常でははないので例外は投げない
                .map(MemoMapper::toListResponse)
                .toList();
    }

    // ID指定にてデータを取得
    public MemoDetailResponse findResponseById(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
    
        return MemoMapper.toDetailResponse(memo);
    }

    // 新規登録
    public MemoResponse create(String title, String content, String tags) {
        Memo saved = memoRepository.save(new Memo(title, content, tags));
        return MemoMapper.toResponse(saved);
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
    
        return MemoMapper.toResponse(saved);
    }

    public void seed() {
        if (memoRepository.count() == 0) {
            memoRepository.save(new Memo("はじめてのメモ", "Day6: GET /memos を作った", "day6,backend"));
            memoRepository.save(new Memo("次にやること", "POST /memos を作る", "todo,api"));
        }
    }
}
