package com.sammie156.collabcanvas.domain.canvas;

import java.util.Objects;

// TODO: What if I could combine Position and Size into one class?
// Like x and y means both position and the x and y size.

/**
 * Stores the size of a node in terms of width and height
 */
public class Size {
    private int width;
    private int height;

    /**
     * Creates an object of Size class
     * @param width
     * @param height
     */
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
