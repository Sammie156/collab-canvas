package com.sammie156.collabcanvas.domain.canvas;

public class Size {
    private double width;
    private double height;

    public Size(double width, double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException(
                "Width and Height cannot be negative"
            );
        }

        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
