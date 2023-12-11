package com.netflix.GifService.objects;

import java.util.ArrayList;

public class GiphyResponse {
    ArrayList<Giphy> data;

    public ArrayList<Giphy> getData() {
        return data;
    }

    public void setData(ArrayList<Giphy> data) {
        this.data = data;
    }
}
