package com.netflix.GifService.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netflix.GifService.Constants;
import com.netflix.GifService.objects.Gif;
import com.netflix.GifService.objects.GifData;
import com.netflix.GifService.objects.Giphy;
import com.netflix.GifService.objects.GiphyResponse;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class GifService {

    private static final String gifSearchUrl = "https://api.giphy.com/v1/gifs/search";

    public GiphyResponse getGiphy(String searchTerm, int offset) throws Exception{
        // Default height for all gifs return
        // TODO: Allow user to enter desired height for gifs returned.
        int height = 270;
        Gson gson = new Gson();

        URI uri = new URIBuilder(URI.create(gifSearchUrl))
                .addParameter("api_key", Constants.API_KEY)
                .addParameter("q", searchTerm)
                .addParameter("limit", "10")
                .addParameter("offset", String.valueOf(offset)).build();

        HttpRequest getRequest = HttpRequest.newBuilder().uri(uri).build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        Type responseType = new TypeToken<GiphyResponse>(){}.getType();
        GiphyResponse giphyResponse = gson.fromJson(getResponse.body(), responseType);

        // Filter results to only data that match the height requirements
        giphyResponse.setData((ArrayList<Giphy>) giphyResponse.getData().stream()
                .filter(c -> c.getImages().getOriginal().getHeight().equals(String.valueOf(height)))
                .collect(Collectors.toList()));

        return giphyResponse;
    }

    public GifData getBatchGifData(String searchTerm) throws Exception {
        // Initial offset of 0
        int offset = 0;
        GifData gifData = new GifData();
        gifData.setSearch_term(searchTerm);

        // Create list of Gifs for searchTerm provided
        ArrayList<Gif> gifList = new ArrayList<>();
        while(gifList.size() < 10){
            GiphyResponse giphyResponse = getGiphy(searchTerm, offset);
            // Convert the response into a list of gifs
            gifList = parseIntoGifList(giphyResponse, gifList);
            // Increase offset by 10, to allow pagination through the search results
            offset = offset + 10;
        }

        gifData.setGifs(gifList);
        return gifData;
    }

    public ArrayList<Gif> parseIntoGifList(GiphyResponse response, ArrayList<Gif> gifs){
        for(Giphy giphy: response.getData()){
            if(gifs.size() < 10){
                gifs.add(new Gif(giphy.getId(), giphy.getEmbed_url()));
            }
        }

        return gifs;
    }
}
