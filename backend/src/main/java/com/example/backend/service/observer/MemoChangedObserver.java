package com.example.backend.service.observer;

public interface MemoChangedObserver {

    void onMemoChanged(MemoChangedEvent event);
    
}
