package com.example.backend.repository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.backend.entity.Memo;

public class MemoSpecifications {

    public static Specification<Memo> titleContains(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                );
    }

    public static Specification<Memo> tagContains(String tag) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("tags")),
                        "%" + tag.toLowerCase() + "%"
                );
    }

    public static Specification<Memo> contentContains(String content) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("content")),
                        "%" + content.toLowerCase() + "%"
                );
    }

    public static Specification<Memo> updatedAtGreaterThanEqual(LocalDate updatedFrom) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("updatedAt"),
                        updatedFrom
                );
    }

    public static Specification<Memo> updatedAtLessThanEqual(LocalDate updatedTo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("updatedAt"),
                        updatedTo
                );
    }

    public static Specification<Memo> favoriteOnly() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("favorite"));
    }

    public static Specification<Memo> archivedOnly() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("archived"));
    }

    private MemoSpecifications() {
    }
    
}
