package com.example.backend.service.observer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemoNotificationPublisher {

    private final List<MemoChangedObserver> observers;

    public void publishMemoChanged(String action) {
        MemoChangedEvent event = new MemoChangedEvent(action);

        for (MemoChangedObserver observer : observers) {
            observer.onMemoChanged(event);
        }
    }
}
