package com.nestor.hibernatevalidation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddCars() throws Exception {
        String json = "{\n" +
                "  \"name\": \"wrapper\",\n" +
                "  \"e\": {\n" +
                "    \"name\": \"特价商品\",\n" +
                "    \"amount\": 9.9\n" +
                "  }\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/cars/ObjectWrapper")
        .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding(StandardCharsets.UTF_8.name())
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.e.length()").value(3))
                .andDo(MockMvcResultHandlers.print());
    }


}