package com.example.backend.controller;

import com.example.backend.repository.MemoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemoControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired MemoRepository memoRepository;

    @BeforeEach
    void setup() {
        memoRepository.deleteAll();
    }

    @Test
    void createMemo_ok() throws Exception {
        String body = """
          {"title":"テスト","content":"本文","tags":"t1"}
        """;

        mockMvc.perform(post("/memos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void createMemo_titleBlank_400() throws Exception {
        String body = """
          {"title":"","content":"本文","tags":"t1"}
        """;

        mockMvc.perform(post("/memos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }
}
