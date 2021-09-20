package com.wrist.source2sea.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutControllerIT {
    @Autowired
    private MockMvc mvc;

    @Test
    void checkoutNotExistingWatch() throws Exception {
        MvcResult result = mvc.perform(
                post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"005\"]"))
                    .andExpect(status().isBadRequest())
                    .andReturn();

        String errorMessage = result.getResponse().getContentAsString();
        assertThat(errorMessage).isEqualTo("Unable to find com.wrist.source2sea.persistence.Watch with id 5");
    }

    @Test
    void checkoutEmptyWatchIds() throws Exception {
        MvcResult result = mvc.perform(
                post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        BigDecimal price = new BigDecimal(result.getResponse().getContentAsString());
        assertThat(price).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void checkoutFiveWatchIds() throws Exception {
        MvcResult result = mvc.perform(
                        post("/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("[\"001\", \"002\", \"001\", \"004\", \"003\"]"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        BigDecimal price = new BigDecimal(result.getResponse().getContentAsString());
        assertThat(price).isEqualTo(BigDecimal.valueOf(360));
    }
}
