package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MemoSseService {

    // 接続中クライアントを保持
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /**
     * クライアント接続時に呼ばれる
     */
    public SseEmitter connect() {
        SseEmitter emitter = new SseEmitter(0L); // タイムアウトなし

        emitters.add(emitter);

        // 切断時に削除
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected"));
        } catch (IOException e) {
            emitters.remove(emitter);
        }

        return emitter;
    }

    /**
     * 全クライアントへ通知
     */
    public void notifyMemoChanged() {
        List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("memo-changed")
                        .data("updated"));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        }

        emitters.removeAll(deadEmitters);
    }
}