package com.example.backend.service.observer;

import org.springframework.stereotype.Component;

import com.example.backend.service.MemoSseService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemoSseObserver implements MemoChangedObserver {

    private final MemoSseService memoSseService;

    @Override
    public void onMemoChanged(MemoChangedEvent event) {
        memoSseService.notifyMemoChanged();
    }
    
}
