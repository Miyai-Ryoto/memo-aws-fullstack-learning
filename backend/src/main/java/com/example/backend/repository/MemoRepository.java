package com.example.backend.repository;

import com.example.backend.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByTitleContainingIgnoreCase(String title);

    List<Memo> findByTagsContainingIgnoreCase(String tags);

    List<Memo> findByContentContainingIgnoreCase(String content);

    List<Memo> findByUpdatedAtBetween(LocalDate updatedFrom, LocalDate updatedTo);

    List<Memo> findByUpdatedAtGreaterThanEqual(LocalDate updatedFrom);
    
    List<Memo> findByUpdatedAtLessThanEqual(LocalDate updatedTo);

    List<Memo> findByFavoriteTrue();

    List<Memo> findByArchivedTrue();
}
