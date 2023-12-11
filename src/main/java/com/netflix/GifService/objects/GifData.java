package com.netflix.GifService.objects;

import java.util.ArrayList;

public class GifData {
    private String search_term;
    private ArrayList<Gif> gifs;

    public String getSearch_term() {
        return search_term;
    }

    public void setSearch_term(String search_term) {
        this.search_term = search_term;
    }

    public ArrayList<Gif> getGifs() {
        return gifs;
    }

    public void setGifs(ArrayList<Gif> gifs) {
        this.gifs = gifs;
    }
}
