package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class EdgeTest {

    @Test
    void shouldConnectTwoNodes() {
        UUID fromNode = UUID.randomUUID();
        UUID toNode = UUID.randomUUID();
        UUID edgeId = UUID.randomUUID();

        Edge edge = new Edge(
                edgeId,
                fromNode,
                null,
                null,
                toNode,
                null,
                null,
                new CanvasColor("3"),
                "label");

        assertEquals(edgeId, edge.getUuid());
        assertEquals(fromNode, edge.getFromNode());
        assertEquals(toNode, edge.getToNode());
    }

    @Test
    void shouldStoreConnectionDetails() {
        Edge edge = new Edge(
                UUID.randomUUID(),
                UUID.randomUUID(),
                EdgeSide.RIGHT,
                EdgeEnd.NONE,
                UUID.randomUUID(),
                EdgeSide.BOTTOM,
                EdgeEnd.ARROW,
                new CanvasColor("3"),
                "label");

        assertEquals(EdgeSide.RIGHT, edge.getFromSide());
        assertEquals(EdgeEnd.NONE, edge.getFromEnd());
        assertEquals(EdgeSide.BOTTOM, edge.getToSide());
        assertEquals(EdgeEnd.ARROW, edge.getToEnd());
    }

    @Test
    void shouldStoreColorAndLabel() {
        Edge edge = new Edge(
            UUID.randomUUID(),
            UUID.randomUUID(),
            null,
            null,
            UUID.randomUUID(),
            null,
            null,
            new CanvasColor("3"),
            "label"
        );

        assertEquals(new CanvasColor("3"), edge.getEdgeColor());
        assertEquals("label", edge.getLabel());
    }
}
