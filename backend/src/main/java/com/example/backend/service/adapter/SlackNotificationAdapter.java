package com.example.backend.service.adapter;

import org.springframework.stereotype.Component;

@Component
public class SlackNotificationAdapter {

    public void sendMemoChangedMessage(String action) {
        String slackJson = createSlackJson(action);
        System.out.println("Slack API用の形式に変換して送信: " + slackJson);
    }

    private String createSlackJson(String action) {
        return """
                {
                  "text": "メモが変更されました。action=%s"
                }
                """.formatted(action);
    }
    
}
