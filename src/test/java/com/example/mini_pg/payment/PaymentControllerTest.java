package com.example.mini_pg.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void 결제_요청하면_201과_READY_상태를_돌려준다() throws Exception {
        mockMvc.perform(post("/payments")
                        .header("Idempotency-Key", "ctrl-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"merchantId":"m1","orderId":"o1","amount":1000}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("READY"))
                .andExpect(jsonPath("$.amount").value(1000));
    }
}
