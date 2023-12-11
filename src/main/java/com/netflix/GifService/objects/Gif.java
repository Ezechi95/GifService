package com.netflix.GifService.objects;

public class Gif {
    private String gif_id;
    private String url;

    public Gif(String gif_id, String url) {
        this.gif_id = gif_id;
        this.url = url;
    }

    public String getGif_id() {
        return gif_id;
    }

    public void setGif_id(String gif_id) {
        this.gif_id = gif_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
