package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class MemoLogService {

    public void writeLog() {
        System.out.println("メモ変更ログを出力しました");
    }
    
}
