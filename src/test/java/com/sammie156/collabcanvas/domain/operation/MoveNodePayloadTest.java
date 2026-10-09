package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sammie156.collabcanvas.domain.canvas.Position;

public class MoveNodePayloadTest {
    @Test
    void shouldStorePosition() {
        Position position = new Position(200, 300);

        MoveNodePayload payload = new MoveNodePayload(position);

        assertEquals(position, payload.getPosition());
    }
}
