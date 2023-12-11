package com.netflix.GifService.objects;

public class Image {

    private OriginalImage original;
    private FixedHeightImage fixed_height;

    public OriginalImage getOriginal() {
        return original;
    }

    public void setOriginal(OriginalImage original) {
        this.original = original;
    }

    public FixedHeightImage getFixed_height() {
        return fixed_height;
    }

    public void setFixed_height(FixedHeightImage fixed_height) {
        this.fixed_height = fixed_height;
    }
}
