package com.netflix.GifService.services;

import com.netflix.GifService.objects.Gif;
import com.netflix.GifService.objects.GifData;
import com.netflix.GifService.objects.GiphyResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@WebMvcTest(GifService.class)
class GifServiceTest {

    @Autowired
    private GifService service;

    @Test
    void testGifService_getGiphy() throws Exception{
        String searchTerm = "a";
        GiphyResponse response = service.getGiphy(searchTerm, 0);

        assertNotNull(response.getData());
    }

    @Test
    void testGifService_getBatchGifData() throws Exception{
        String searchTerm = "a";
        GifData data = service.getBatchGifData(searchTerm);

        assertNotNull(data);
        assertEquals(searchTerm, data.getSearch_term());
        assertEquals(10, data.getGifs().size());
    }

    @Test
    void testGifService_parseIntoGifList() throws Exception{
        String searchTerm = "a";
        GiphyResponse response = service.getGiphy(searchTerm, 0);
        ArrayList<Gif> gifs = new ArrayList<>();

        ArrayList<Gif> parsedResponse = service.parseIntoGifList(response, gifs);

        assertEquals(response.getData().get(0).getId(), parsedResponse.get(0).getGif_id());
        assertEquals(response.getData().size(), parsedResponse.size());
    }
}