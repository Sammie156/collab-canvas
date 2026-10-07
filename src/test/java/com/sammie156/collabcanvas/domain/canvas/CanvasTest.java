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

    @Test
    void shouldAddNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        Node node = new TextNode(UUID.randomUUID(),
                new Position(200, 300),
                new Size(10, 12),
                new CanvasColor("3"),
                "Hello Node");
        
        canvas.addNode(node);

        assertEquals(1, canvas.getNodes().size());
        assertEquals(node, canvas.getNodes().get(0));
    }

    @Test
    void shouldRejectDuplicateNodes() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        UUID nodeId = UUID.randomUUID();

        Node firstNode = new TextNode(
            nodeId,
            new Position(100, 200),
            new Size(10, 12),
            new CanvasColor("3"),
            "Hello Node"
        );
        
        Node secondNode = new TextNode(
            nodeId,
            new Position(100, 200),
            new Size(10, 12),
            new CanvasColor("3"),
            "Hello Node"
        );

        canvas.addNode(firstNode);

        assertThrows(
            IllegalArgumentException.class,
            () -> canvas.addNode(secondNode)
        );
    }

    @Test
    void shouldAddEdge() {
        Node fromNode = new TextNode(
            UUID.randomUUID(), 
            new Position(100, 300), 
            new Size(10, 12), 
            new CanvasColor("3"),
            "Hello Node"
        );

        Node toNode = new TextNode(
            UUID.randomUUID(), 
            new Position(12, 13), 
            new Size(10, 43), 
            new CanvasColor("5"),
            "Another Node"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        canvas.addNode(fromNode);
        canvas.addNode(toNode);

        Edge edge = new Edge(
            UUID.randomUUID(),
            fromNode.getUuid(),
            null,
            null,
            toNode.getUuid(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        canvas.addEdge(edge);

        assertEquals(fromNode.getUuid(), edge.getFromNode());
        assertEquals(toNode.getUuid(), edge.getToNode());
    }

    @Test
    void shouldRejectInvalidToNode() {
        Node fromNode = new TextNode(
            UUID.randomUUID(), 
            new Position(100, 300), 
            new Size(10, 12), 
            new CanvasColor("3"),
            "Hello Node"
        );

        Node toNode = new TextNode(
            UUID.randomUUID(), 
            new Position(100, 300), 
            new Size(10, 12), 
            new CanvasColor("3"),
            "Hello Node"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        canvas.addNode(fromNode);

        Edge edge = new Edge(
            UUID.randomUUID(),
            fromNode.getUuid(),
            null,
            null,
            toNode.getUuid(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        assertThrows(IllegalArgumentException.class, () -> canvas.addEdge(edge));
    }

    @Test
    void shouldRejectInvalidFromNode() {
        Node fromNode = new TextNode(
            UUID.randomUUID(), 
            new Position(100, 300), 
            new Size(10, 12), 
            new CanvasColor("3"),
            "Hello Node"
        );

        Node toNode = new TextNode(
            UUID.randomUUID(), 
            new Position(100, 300), 
            new Size(10, 12), 
            new CanvasColor("3"),
            "Hello Node"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        canvas.addNode(toNode);

        Edge edge = new Edge(
            UUID.randomUUID(),
            fromNode.getUuid(),
            null,
            null,
            toNode.getUuid(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        assertThrows(IllegalArgumentException.class, () -> canvas.addEdge(edge));
    }

    @Test
    void shouldRejectDuplicateNode() {
        Node fromNode = new TextNode(
            UUID.randomUUID(), 
            new Position(100, 300), 
            new Size(10, 12), 
            new CanvasColor("3"),
            "Hello Node"
        );

        Node toNode = new TextNode(
            UUID.randomUUID(), 
            new Position(12, 13), 
            new Size(10, 43), 
            new CanvasColor("5"),
            "Another Node"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        canvas.addNode(fromNode);
        canvas.addNode(toNode);

        UUID edgeId = UUID.randomUUID();

        Edge firstEdge = new Edge(
            edgeId,
            fromNode.getUuid(),
            null,
            null,
            toNode.getUuid(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        Edge secondEdge = new Edge(
            edgeId,
            fromNode.getUuid(),
            null,
            null,
            toNode.getUuid(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        canvas.addEdge(firstEdge);
        
        assertThrows(IllegalArgumentException.class, () -> canvas.addEdge(secondEdge));
    }
}
