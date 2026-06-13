package com.example.backend.service.observer;

public class MemoChangedEvent {

    private final String action;

    public MemoChangedEvent(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
    
}
