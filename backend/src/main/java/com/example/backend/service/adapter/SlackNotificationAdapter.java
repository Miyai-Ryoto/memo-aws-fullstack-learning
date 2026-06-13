package com.example.backend.service.adapter;

import org.springframework.stereotype.Component;

@Component
public class SlackNotificationAdapter {

    public void sendMemoChangedMessage(String action) {
        System.out.println("Slack API用の形式に変換して送信: " + action);
    }
    
}
