package com.example.backend.service.observer;

import com.example.backend.service.MemoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoLogObserver implements MemoChangedObserver {

    private final MemoLogService memoLogService;

    @Override
    public void onMemoChanged(MemoChangedEvent event) {
        memoLogService.writeLog();
    }
}