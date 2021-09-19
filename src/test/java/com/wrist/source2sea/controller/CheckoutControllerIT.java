package com.wrist.source2sea.controller;

import com.wrist.source2sea.repository.WatchRepository;
import com.wrist.source2sea.service.WatchPriceCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CheckoutController.class, WatchRepository.class, WatchPriceCalculator.class})
@WebMvcTest
public class CheckoutControllerIT {
    @Autowired
    private MockMvc mvc;

    @Test
    void checkoutNotExistingWatch() {
        // TODO: change to http status 400 when using jpa
        assertThatThrownBy(() -> {
            mvc.perform(
                post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"005\"]"));
        }).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasRootCauseMessage("Invalid watch id 005 !");
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
