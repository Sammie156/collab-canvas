package com.sammie156.collabcanvas.domain.canvas;

import java.util.Objects;

/**
 * Value object class to store the x and y position of a node in the canvas
 */
public class Position {
    private int x;
    private int y;

    /**
     * Creates an object of Position type
     * @param x x co-ordinate
     * @param y y co-ordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Position other)) {
            return false;
        }

        return x == other.x && y == other.y;
    }

    @Override 
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
