package com.sammie156.collabcanvas.domain.canvas;

public class Size {
    private int width;
    private int height;

    public Size(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException(
                "Width and Height cannot be negative"
            );
        }

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
