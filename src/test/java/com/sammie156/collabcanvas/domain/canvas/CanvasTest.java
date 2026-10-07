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
            fromNode.getId(),
            null,
            null,
            toNode.getId(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        canvas.addEdge(edge);

        assertEquals(fromNode.getId(), edge.getFromNode());
        assertEquals(toNode.getId(), edge.getToNode());
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
            fromNode.getId(),
            null,
            null,
            toNode.getId(),
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
            fromNode.getId(),
            null,
            null,
            toNode.getId(),
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
            fromNode.getId(),
            null,
            null,
            toNode.getId(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        Edge secondEdge = new Edge(
            edgeId,
            fromNode.getId(),
            null,
            null,
            toNode.getId(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        canvas.addEdge(firstEdge);
        
        assertThrows(IllegalArgumentException.class, () -> canvas.addEdge(secondEdge));
    }

    @Test
    void shouldRemoveNode() {
        Node node = new TextNode(
            UUID.randomUUID(),
            new Position(100, 200),
            new Size(10, 12),
            new CanvasColor("3"),
            "node"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        canvas.addNode(node);
        canvas.removeNode(node);

        assertTrue(canvas.getNodes().isEmpty());
    }

    @Test
    void shouldRejectRemovingInvalidNode() {
        Node node = new TextNode(
            UUID.randomUUID(),
            new Position(100, 200),
            new Size(10, 12),
            new CanvasColor("3"),
            "node"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        assertThrows(IllegalArgumentException.class, () -> canvas.removeNode(node));
    }

    @Test
    void shouldRemoveEdgeWhenDeletingNode() {
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

        Edge edge = new Edge(
            edgeId,
            fromNode.getId(),
            null,
            null,
            toNode.getId(),
            null,
            null,
            new CanvasColor("3"),
            "edge"
        );

        canvas.addEdge(edge);
        canvas.removeNode(fromNode);

        assertTrue(canvas.getEdges().isEmpty());
    }

    @Test
    void shouldRemoveEdgesOnBothSideWhenRemoveNode() {
        Node nodeA = new TextNode(
            UUID.randomUUID(),
            new Position(100, 200),
            new Size(10, 12),
            new CanvasColor("3"),
            "first node"
        );

        Node nodeB = new TextNode(
            UUID.randomUUID(),
            new Position(105, 300),
            new Size(23, 30),
            new CanvasColor("4"),
            "second node"
        );

        Node nodeC = new TextNode(
            UUID.randomUUID(),
            new Position(200, 321),
            new Size(28, 31),
            new CanvasColor("6"),
            "third node"
        );

        Edge firstEdge = new Edge(
            UUID.randomUUID(),
            nodeA.getId(),
            null,
            null,
            nodeB.getId(),
            null,
            null,
            new CanvasColor("1"),
            "edge1"
        );

        Edge secondEdge = new Edge(
            UUID.randomUUID(),
            nodeB.getId(),
            null,
            null,
            nodeC.getId(),
            null,
            null,
            new CanvasColor("2"),
            "edge2"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        canvas.addNode(nodeA);
        canvas.addNode(nodeB);
        canvas.addNode(nodeC);

        canvas.addEdge(firstEdge);
        canvas.addEdge(secondEdge);

        assertEquals(3, canvas.getNodes().size());
        assertEquals(2, canvas.getEdges().size());

        canvas.removeNode(nodeB);

        assertTrue(canvas.getEdges().isEmpty());
    }

    @Test
    void shouldRemoveEdge() {
        Node nodeA = new TextNode(
            UUID.randomUUID(),
            new Position(100, 200),
            new Size(10, 12),
            new CanvasColor("3"),
            "first node"
        );

        Node nodeB = new TextNode(
            UUID.randomUUID(),
            new Position(105, 300),
            new Size(23, 30),
            new CanvasColor("4"),
            "second node"
        );

        Edge edge = new Edge(
            UUID.randomUUID(),
            nodeA.getId(),
            null,
            null,
            nodeB.getId(),
            null,
            null,
            new CanvasColor("1"),
            "edge1"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        canvas.addNode(nodeA);
        canvas.addNode(nodeB);
        canvas.addEdge(edge);

        canvas.removeEdge(edge);

        assertTrue(canvas.getEdges().isEmpty());
    }

    @Test
    void shouldRejectRemovingInvalidEdge() {
        Node nodeA = new TextNode(
            UUID.randomUUID(),
            new Position(100, 200),
            new Size(10, 12),
            new CanvasColor("3"),
            "first node"
        );

        Node nodeB = new TextNode(
            UUID.randomUUID(),
            new Position(105, 300),
            new Size(23, 30),
            new CanvasColor("4"),
            "second node"
        );

        Edge edge = new Edge(
            UUID.randomUUID(),
            nodeA.getId(),
            null,
            null,
            nodeB.getId(),
            null,
            null,
            new CanvasColor("1"),
            "edge1"
        );

        Canvas canvas = new Canvas(UUID.randomUUID(), "canvas");

        assertThrows(
            IllegalArgumentException.class,
            () -> canvas.removeEdge(edge)
        );
    }
}
