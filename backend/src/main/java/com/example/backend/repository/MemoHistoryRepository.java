package com.example.backend.repository;

import com.example.backend.entity.MemoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoHistoryRepository extends JpaRepository<MemoHistory, Long> {

    Optional<MemoHistory> findTopByMemoIdOrderBySavedAtDesc(Long memoId);
}