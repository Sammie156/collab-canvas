package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test 
    void shouldStoreCoordinates() {
        Position position = new Position(100, 200);

        assertEquals(100, position.getX());
        assertEquals(200, position.getY());
    }

    @Test
    void shouldStoreNegativeCoordinates() {
        Position position = new Position(-500, -200);

        assertEquals(-500, position.getX());
        assertEquals(-200, position.getY());
    }
}
