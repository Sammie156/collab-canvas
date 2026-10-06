package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SizeTest {

    @Test
    void shouldStoreSize() {
        Size size = new Size(100, 200);

        assertEquals(100, size.getWidth());
        assertEquals(200, size.getHeight());
    }

    @Test
    void shouldRejectNegativeWidth() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Size(-100, 200)
        );
    }

    @Test
    void shouldRejectNegativeHeight() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Size(100, -200)
        );
    }
}
