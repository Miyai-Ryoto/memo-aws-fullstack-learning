package com.example.backend.service.command;

public interface MemoCommand<T> {

    T execute();
}