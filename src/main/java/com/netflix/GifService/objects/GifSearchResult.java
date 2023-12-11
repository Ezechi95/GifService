package com.netflix.GifService.objects;

import java.util.ArrayList;

public class GifSearchResult {
    private ArrayList<GifData> data;

    public GifSearchResult(ArrayList<GifData> data) {
        this.data = data;
    }

    public ArrayList<GifData> getData() {
        return data;
    }

    public void setData(ArrayList<GifData> data) {
        this.data = data;
    }
}
