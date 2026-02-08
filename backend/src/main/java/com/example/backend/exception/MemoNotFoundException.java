package com.example.backend.exception;

public class MemoNotFoundException extends RuntimeException {
    public MemoNotFoundException(Long id) {
        super("Memo not found. id=" + id);
    }
}
