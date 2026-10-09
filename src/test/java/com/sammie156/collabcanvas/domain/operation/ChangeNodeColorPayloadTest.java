package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sammie156.collabcanvas.domain.canvas.CanvasColor;

public class ChangeNodeColorPayloadTest {
    @Test
    void shouldStoreColor() {
        CanvasColor color = new CanvasColor("3");

        ChangeNodeColorPayload payload = new ChangeNodeColorPayload(color);

        assertEquals(color, payload.getColor());
    }
}
