package com.sammie156.collabcanvas.domain.canvas;

import java.util.Objects;

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

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Size other)) {
            return false;
        }

        return width == other.width && height == other.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
