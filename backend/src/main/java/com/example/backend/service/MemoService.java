package com.example.backend.service;

import com.example.backend.dto.CreateMemoRequest;
import com.example.backend.dto.MemoDetailResponse;
import com.example.backend.dto.MemoListResponce;
import com.example.backend.dto.MemoResponse;
import com.example.backend.dto.MemoSearchCondition;
import com.example.backend.dto.request.MemoStatus;
import com.example.backend.dto.request.MemoType;
import com.example.backend.entity.Memo;
import com.example.backend.entity.MemoHistory;
import com.example.backend.exception.MemoNotFoundException;
import com.example.backend.mapper.MemoMapper;
import com.example.backend.repository.MemoHistoryRepository;
import com.example.backend.repository.MemoRepository;
import com.example.backend.repository.specification.MemoSpecifications;
import com.example.backend.service.state.MemoState;
import com.example.backend.service.state.MemoStateResolver;
import com.example.backend.service.strategy.MemoSortStrategyResolver;
import com.example.backend.service.template.MemoCreateTemplateResolver;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final MemoHistoryRepository memoHistoryRepository;
    private final MemoSortStrategyResolver memoSortStrategyResolver;
    private final MemoCreateTemplateResolver memoCreateTemplateResolver;
    private final MemoStateResolver memoStateResolver;

    // 全件データ取得
    public List<MemoListResponce> findAllResponses() {
        return memoRepository.findAll().stream() // データが0件でも異常でははないので例外は投げない
                .map(MemoMapper::toListResponse)
                .toList();
    }

    // 単件データ取得
    public MemoDetailResponse findResponseById(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
    
        return MemoMapper.toDetailResponse(memo);
    }

    // 検索
    public List<MemoListResponce> searchResponses(MemoSearchCondition condition) {

        List<Specification<Memo>> specs = new ArrayList<>();
    
        if (condition.hasTitle()) {
            specs.add(MemoSpecifications.titleContains(condition.getTitle()));
        }
    
        if (condition.hasTag()) {
            specs.add(MemoSpecifications.tagContains(condition.getTag()));
        }
    
        if (condition.hasContent()) {
            specs.add(MemoSpecifications.contentContains(condition.getContent()));
        }
    
        if (condition.getUpdatedFrom() != null) {
            specs.add(MemoSpecifications.updatedAtGreaterThanEqual(condition.getUpdatedFrom()));
        }
    
        if (condition.getUpdatedTo() != null) {
            specs.add(MemoSpecifications.updatedAtLessThanEqual(condition.getUpdatedTo()));
        }
    
        if (condition.isFavoriteOnly()) {
            specs.add(MemoSpecifications.favoriteOnly());
        }
    
        if (condition.isArchivedOnly()) {
            specs.add(MemoSpecifications.archivedOnly());
        }

        Specification<Memo> spec = specs.stream()
                .reduce(Specification::and)
                .orElse(null);

        List<Memo> memos = memoRepository.findAll(spec);

        List<Memo> sortedMemos = memoSortStrategyResolver
                .resolve(condition.getSort())
                .map(strategy -> memos.stream()
                        .sorted(strategy.getComparator())
                        .toList())
                .orElse(memos);
        
        return sortedMemos.stream()
                .map(MemoMapper::toListResponse)
                .toList();
    }

    // 新規登録
    public MemoResponse create(CreateMemoRequest request) {
        MemoType memoType = MemoType.from(request.getType());

        return memoCreateTemplateResolver
                .resolve(memoType)
                .create(request);
    }

    // 削除
    @Transactional
    public void deleteMemo(Long id) {
        // 存在チェックして、無ければ 404 にしたいので例外
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
        
        MemoState memoState = memoStateResolver.resolve(memo.getStatus());
        memoState.delete(memo);
    
        memoRepository.delete(memo);
    }

    // 更新
    @Transactional
    public MemoResponse update(Long id, String title, String content, String tags) {
    
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
        
        MemoHistory memoHistory = new MemoHistory(memo);
        memoHistoryRepository.save(memoHistory);

        MemoState memoState = memoStateResolver.resolve(memo.getStatus());
        memoState.update(memo, title, content, tags);
    
        Memo saved = memoRepository.save(memo);
    
        return MemoMapper.toResponse(saved);
    }

    // 公開
    @Transactional
    public MemoResponse publishMemo(Long id) {
    
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
        
        MemoState memoState = memoStateResolver.resolve(memo.getStatus());
        memoState.publish(memo);
    
        Memo saved = memoRepository.save(memo);
    
        return MemoMapper.toResponse(saved);
    }

    // アーカイブ
    @Transactional
    public MemoResponse archiveMemo(Long id) {
    
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
    
        MemoState memoState = memoStateResolver.resolve(memo.getStatus());
        memoState.archive(memo);
    
        Memo saved = memoRepository.save(memo);
    
        return MemoMapper.toResponse(saved);
    }

    // 復元
    @Transactional
    public MemoResponse restoreMemo(Long id) {
    
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
        
        MemoState memoState = memoStateResolver.resolve(memo.getStatus());
        memoState.restore(memo);
    
        Memo saved = memoRepository.save(memo);
    
        return MemoMapper.toResponse(saved);
    }

    public void seed() {
        if (memoRepository.count() == 0) {
            memoRepository.save(new Memo(MemoStatus.DRAFT, "はじめてのメモ", "Day6: GET /memos を作った", "day6,backend"));
            memoRepository.save(new Memo(MemoStatus.DRAFT, "次にやること", "POST /memos を作る", "todo,api"));
        }
    }
}
