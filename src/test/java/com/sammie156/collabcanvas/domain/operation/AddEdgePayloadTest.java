package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.sammie156.collabcanvas.domain.canvas.CanvasColor;
import com.sammie156.collabcanvas.domain.canvas.Edge;

public class AddEdgePayloadTest {
    @Test
    void shouldStoreEdge() {
        Edge edge = new Edge(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            null,
            null,
            UUID.randomUUID().toString(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        AddEdgePayload payload = new AddEdgePayload(edge);

        assertEquals(edge, payload.getEdge());
    }
}
