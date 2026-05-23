package com.example.backend.dto;

import java.time.LocalDate;

import com.example.backend.dto.request.MemoSortType;

public class MemoSearchCondition {

    private String title;
    private String tag;
    private String content;
    private LocalDate updatedFrom;
    private LocalDate updatedTo;
    private Boolean favoriteOnly;
    private Boolean archivedOnly;
    private MemoSortType sort;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getUpdatedFrom() {
        return updatedFrom;
    }

    public void setUpdatedFrom(LocalDate updatedFrom) {
        this.updatedFrom = updatedFrom;
    }

    public LocalDate getUpdatedTo() {
        return updatedTo;
    }

    public void setUpdatedTo(LocalDate updatedTo) {
        this.updatedTo = updatedTo;
    }

    public Boolean getFavoriteOnly() {
        return favoriteOnly;
    }

    public void setFavoriteOnly(Boolean favoriteOnly) {
        this.favoriteOnly = favoriteOnly;
    }

    public Boolean getArchivedOnly() {
        return archivedOnly;
    }

    public void setArchivedOnly(Boolean archivedOnly) {
        this.archivedOnly = archivedOnly;
    }

    public MemoSortType getSort() {
        return sort;
    }

    public void setSort(MemoSortType sort) {
        this.sort = sort;
    }

    public boolean hasCondition() {
        return hasText(title)
                || hasText(tag)
                || hasText(content)
                || updatedFrom != null
                || updatedTo != null
                || favoriteOnly != null
                || archivedOnly != null
                || sort != null;
    }

    public boolean hasTitle() {
        return hasText(title);
    }

    public boolean hasTag() {
        return hasText(tag);
    }

    public boolean hasContent() {
        return hasText(content);
    }

    public boolean isFavoriteOnly() {
        return Boolean.TRUE.equals(favoriteOnly);
    }

    public boolean isArchivedOnly() {
        return Boolean.TRUE.equals(archivedOnly);
    }

    public boolean hasSort() {
        return sort != null;
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}