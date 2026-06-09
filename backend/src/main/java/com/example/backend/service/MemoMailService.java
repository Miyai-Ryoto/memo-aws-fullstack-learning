package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class MemoMailService {

    public void send() {
        System.out.println("メール通知を送信しました");
    }
    
}
