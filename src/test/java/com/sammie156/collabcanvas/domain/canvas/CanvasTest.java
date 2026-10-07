package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class CanvasTest {
    @Test
    void shouldCreateEmptyCanvas() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        assertTrue(canvas.getNodes().isEmpty());
        assertTrue(canvas.getEdges().isEmpty());
    }

    @Test
    void shouldStoreCanvasDetails() {
        UUID uuid = UUID.randomUUID();

        Canvas canvas = new Canvas(uuid, "canvas");

        assertEquals(uuid, canvas.getId());
        assertEquals("canvas", canvas.getName());
    }

    @Test
    void shouldNotAllowModificationOfLists() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        assertThrows(UnsupportedOperationException.class, () -> canvas.getNodes().add(null));
        assertThrows(UnsupportedOperationException.class, () -> canvas.getEdges().add(null));
    }
}
