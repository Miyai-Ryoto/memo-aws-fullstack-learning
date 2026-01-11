package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class HealthService {
    public String checkHealth() {
        return "OK";
    }
}