package com.example.backend.repository;

import com.example.backend.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByTitleContainingIgnoreCase(String title);

    List<Memo> findByTagsContainingIgnoreCase(String tags);
}
