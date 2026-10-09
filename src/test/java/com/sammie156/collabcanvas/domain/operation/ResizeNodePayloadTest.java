package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sammie156.collabcanvas.domain.canvas.Size;

public class ResizeNodePayloadTest {
    @Test
    void shouldStoreSize() {
        Size size = new Size(200, 300);

        ResizeNodePayload payload = new ResizeNodePayload(size);

        assertEquals(size, payload.getSize());
    }
}
