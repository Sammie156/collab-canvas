package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CanvasColorTest {
    @Test
    void shouldAcceptPresetColor() {
        CanvasColor color = new CanvasColor("3");

        assertEquals("3", color.getValue());
    }

    @Test
    void shouldAcceptHexValue() {
        CanvasColor color = new CanvasColor("#30a4d5");

        assertEquals("#30a4d5", color.getValue());
    }

    @Test
    void shouldRejectArbitraryValue() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CanvasColor("7"));
    }

    @Test
    void shouldRejectWrongHexValue() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CanvasColor("#GGGGGG"));
    }

    @Test
    void shouldRejectArbitraryString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CanvasColor("banana"));
    }

    @Test
    void shouldRejectNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CanvasColor(null));
    }
}
