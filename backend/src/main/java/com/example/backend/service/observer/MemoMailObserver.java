package com.example.backend.service.observer;

import com.example.backend.service.MemoMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoMailObserver implements MemoChangedObserver {

    private final MemoMailService memoMailService;

    @Override
    public void onMemoChanged(MemoChangedEvent event) {
        memoMailService.send();
    }
}