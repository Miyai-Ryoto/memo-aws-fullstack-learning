package com.example.backend.service.observer;

import com.example.backend.service.adapter.SlackNotificationAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoSlackObserver implements MemoChangedObserver {

    private final SlackNotificationAdapter slackNotificationAdapter;

    @Override
    public void onMemoChanged(MemoChangedEvent event) {
        slackNotificationAdapter.sendMemoChangedMessage(event.getAction());
    }
}