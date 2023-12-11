package com.netflix.GifService.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GifController.class)
class GifControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void testControllerResponse_isOkWithValidSearchQuery() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/query?searchTerm=a");
        mock.perform(request).andExpect(status().isOk());
    }

    @Test
    void testControllerResponse_isNotOkWIthInvalidSearchQuery() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/query");
        mock.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void testControllerResponse_containsOneSearchResult() throws Exception{
        String term = "a";
        RequestBuilder request = MockMvcRequestBuilders.get("/query?searchTerm="+ term);
        mock.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[*].search_term").value(term))
                .andExpect(jsonPath("$.data[*].gifs").exists());
    }

    @Test
    void testControllerResponse_containsTwoSearchResults() throws Exception{
        ArrayList<String> termList = new ArrayList<>(Arrays.asList("a", "b"));
        RequestBuilder request = MockMvcRequestBuilders.get("/query?searchTerm="+ termList.get(0) + "&searchTerm=" + termList.get(1));
        mock.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[*]").isArray())
                .andExpect(jsonPath("$.data[*].search_term").value(termList))
                .andExpect(jsonPath("$.data[*].gifs").exists());
    }

    @Test
    void testControllerResponse_containsThreeSearchResults() throws Exception{
        ArrayList<String> termList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        RequestBuilder request = MockMvcRequestBuilders.get("/query?searchTerm="+ termList.get(0) +"&searchTerm="+ termList.get(1) +"&searchTerm="+ termList.get(2));
        mock.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[*]").isArray())
                .andExpect(jsonPath("$.data[*].search_term").value(termList))
                .andExpect(jsonPath("$.data[*].gifs").exists());
    }
}