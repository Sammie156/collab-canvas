package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.sammie156.collabcanvas.domain.canvas.Canvas;
import com.sammie156.collabcanvas.domain.canvas.Edge;
import com.sammie156.collabcanvas.domain.canvas.CanvasColor;
import com.sammie156.collabcanvas.domain.canvas.FileNode;
import com.sammie156.collabcanvas.domain.canvas.GroupNode;
import com.sammie156.collabcanvas.domain.canvas.LinkNode;
import com.sammie156.collabcanvas.domain.canvas.Position;
import com.sammie156.collabcanvas.domain.canvas.Size;
import com.sammie156.collabcanvas.domain.canvas.TextNode;
import com.sammie156.collabcanvas.domain.operation.UpdateNodeContentPayload.FileContent;
import com.sammie156.collabcanvas.domain.operation.UpdateNodeContentPayload.GroupContent;
import com.sammie156.collabcanvas.domain.operation.UpdateNodeContentPayload.LinkContent;
import com.sammie156.collabcanvas.domain.operation.UpdateNodeContentPayload.TextContent;

import org.junit.jupiter.api.Test;

public class CanvasOperationApplierTest {
    @Test
    void shouldAddNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(300, 200),
                new Size(20, 14),
                new CanvasColor("3"),
                "testnode");

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.ADD_NODE,
                null,
                new AddNodePayload(node),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();
        applier.apply(canvas, operation);

        assertEquals(1, canvas.getNodes().size());
        assertEquals(node.getId(), canvas.getNodes().get(0).getId());
    }

    @Test
    void shouldRemoveNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 20),
                new CanvasColor("2"),
                "test node");

        Operation addNodeOperation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.ADD_NODE,
                null,
                new AddNodePayload(node),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();
        applier.apply(canvas, addNodeOperation);

        Operation removeNodeOperation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.REMOVE_NODE,
                node.getId(),
                null,
                1L);

        applier.apply(canvas, removeNodeOperation);

        assertTrue(canvas.getNodes().isEmpty());
    }

    @Test
    void shouldAddEdge() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node1 = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 20),
                new CanvasColor("3"),
                "a node");
        FileNode node2 = new FileNode(
                UUID.randomUUID(),
                new Position(200, 150),
                new Size(10, 15),
                new CanvasColor("2"),
                "path/to/file",
                null);

        canvas.addNode(node1);
        canvas.addNode(node2);

        Edge edge = new Edge(
                UUID.randomUUID(),
                node1.getId(),
                null,
                null,
                node2.getId(),
                null,
                null,
                new CanvasColor("2"),
                "edge");

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.ADD_EDGE,
                null,
                new AddEdgePayload(edge),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();
        applier.apply(canvas, operation);

        assertEquals(1, canvas.getEdges().size());
    }

    @Test
    void shouldRejectRemovingNonexistentEdge() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.REMOVE_EDGE,
                UUID.randomUUID(),
                null,
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        assertThrows(
                IllegalArgumentException.class,
                () -> applier.apply(canvas, operation));
    }

    @Test
    void shouldRejectAddingEdgeWithMissingEndpoint() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node1 = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 20),
                new CanvasColor("3"),
                "a node");

        canvas.addNode(node1);

        Edge edge = new Edge(
                UUID.randomUUID(),
                node1.getId(),
                null,
                null,
                UUID.randomUUID(),
                null,
                null,
                new CanvasColor("3"),
                "edge");

        CanvasOperationApplier applier = new CanvasOperationApplier();

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.ADD_EDGE,
                null,
                new AddEdgePayload(edge),
                1L);

        assertThrows(
                IllegalArgumentException.class,
                () -> applier.apply(canvas, operation));
    }

    @Test
    void shouldRemoveEdge() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node1 = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 20),
                new CanvasColor("3"),
                "a node");
        FileNode node2 = new FileNode(
                UUID.randomUUID(),
                new Position(200, 150),
                new Size(10, 15),
                new CanvasColor("2"),
                "path/to/file",
                null);

        canvas.addNode(node1);
        canvas.addNode(node2);

        Edge edge = new Edge(
                UUID.randomUUID(),
                node1.getId(),
                null,
                null,
                node2.getId(),
                null,
                null,
                new CanvasColor("2"),
                "edge");

        canvas.addEdge(edge);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.REMOVE_EDGE,
                edge.getId(),
                null,
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, operation);

        assertTrue(canvas.getEdges().isEmpty());
    }

    @Test
    void shouldRemoveConnectedEdgeWhenRemovingNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node1 = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 20),
                new CanvasColor("3"),
                "a node");
        FileNode node2 = new FileNode(
                UUID.randomUUID(),
                new Position(200, 150),
                new Size(10, 15),
                new CanvasColor("2"),
                "path/to/file",
                null);

        canvas.addNode(node1);
        canvas.addNode(node2);

        Edge edge = new Edge(
                UUID.randomUUID(),
                node1.getId(),
                null,
                null,
                node2.getId(),
                null,
                null,
                new CanvasColor("2"),
                "edge");

        canvas.addEdge(edge);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.REMOVE_NODE,
                node2.getId(),
                null,
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();
        applier.apply(canvas, operation);

        assertTrue(canvas.getEdges().isEmpty());
    }

    @Test
    void shouldMoveNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 16),
                new CanvasColor("3"),
                "test node");

        Operation addNodeOperation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.ADD_NODE,
                null,
                new AddNodePayload(node),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, addNodeOperation);

        Operation moveNodeOperation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.MOVE_NODE,
                node.getId(),
                new MoveNodePayload(new Position(16, 10)),
                2L);

        applier.apply(canvas, moveNodeOperation);

        assertEquals(new Position(16, 10), node.getPosition());
    }

    @Test
    void shouldRejectOperationForDifferentCanvas() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 10),
                new CanvasColor("3"),
                "test node");

        Operation operation = new Operation(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                OperationType.ADD_NODE,
                null,
                new AddNodePayload(node),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        assertThrows(
                IllegalArgumentException.class,
                () -> applier.apply(canvas, operation));
    }

    @Test
    void shouldRejectMovingNonExistentNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.MOVE_NODE,
                UUID.randomUUID(),
                new MoveNodePayload(new Position(20, 10)),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        assertThrows(
                IllegalArgumentException.class,
                () -> applier.apply(canvas, operation));
    }

    @Test
    void shouldRejectUnsupportedOperation() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.UNKNOWN_OPERATION,
                null,
                null,
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        assertThrows(
                UnsupportedOperationException.class,
                () -> applier.apply(canvas, operation));
    }

    @Test
    void shouldResizeNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 10),
                new CanvasColor("1"),
                "test node");

        canvas.addNode(node);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.RESIZE_NODE,
                node.getId(),
                new ResizeNodePayload(new Size(15, 15)),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, operation);

        assertEquals(new Size(15, 15), node.getSize());
    }

    @Test
    void shouldChangeNodeColor() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 10),
                new CanvasColor("1"),
                "test node");

        canvas.addNode(node);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.CHANGE_NODE_COLOR,
                node.getId(),
                new ChangeNodeColorPayload(new CanvasColor("5")),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, operation);

        assertEquals(new CanvasColor("5"), node.getNodeColor());
    }

    @Test
    void shouldRejectResizingNonExistingNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.RESIZE_NODE,
                UUID.randomUUID(),
                new ResizeNodePayload(new Size(100, 200)),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        assertThrows(
                IllegalArgumentException.class,
                () -> applier.apply(canvas, operation));
    }

    @Test
    void shouldrejectChangingColorOfNonexistentNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.CHANGE_NODE_COLOR,
                UUID.randomUUID(),
                new ChangeNodeColorPayload(new CanvasColor("3")),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        assertThrows(
                IllegalArgumentException.class,
                () -> applier.apply(canvas, operation));
    }

    // TODO: Add these tests below

    @Test
    void shouldUpdateTextNodeContent() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(10, 20),
                new Size(10, 15),
                new CanvasColor("3"),
                "test");

        canvas.addNode(node);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.UPDATE_NODE_CONTENT,
                node.getId(),
                new UpdateNodeContentPayload(
                        new TextContent("Another test")),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, operation);

        assertEquals("Another test", node.getText());
    }

    @Test
    void shouldUpdateFileNodeContent() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        FileNode node = new FileNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 14),
                new CanvasColor("3"),
                "initial/path",
                null);

        canvas.addNode(node);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.UPDATE_NODE_CONTENT,
                node.getId(),
                new UpdateNodeContentPayload(
                        new FileContent("new/path", "another/path")),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, operation);

        assertEquals("new/path", node.getFile());
        assertEquals("another/path", node.getSubpath());
    }

    @Test
    void shouldUpdateLinkNodeContent() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        LinkNode node = new LinkNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 15),
                new CanvasColor("1"),
                "google.com");

        canvas.addNode(node);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.UPDATE_NODE_CONTENT,
                node.getId(),
                new UpdateNodeContentPayload(
                        new LinkContent("youtube.com")),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, operation);

        assertEquals("youtube.com", node.getUrl());
    }

    @Test
    void shouldUpdateGroupNodeContent() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        GroupNode node = new GroupNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 15),
                new CanvasColor("3"),
                "group",
                null,
                null);

        canvas.addNode(node);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.UPDATE_NODE_CONTENT,
                node.getId(),
                new UpdateNodeContentPayload(
                        new GroupContent("new group", "image", null)),
                1L);

        CanvasOperationApplier applier = new CanvasOperationApplier();

        applier.apply(canvas, operation);

        assertEquals("new group", node.getLabel());
        assertEquals("image", node.getBackground());
        assertNull(node.getBackgroundStyle());
    }

    @Test
    void shouldRejectContentTypeMismatch() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        TextNode node = new TextNode(
                UUID.randomUUID(),
                new Position(100, 100),
                new Size(10, 15),
                new CanvasColor("3"),
                "test");

        canvas.addNode(node);

        Operation operation = new Operation(
                UUID.randomUUID(),
                canvas.getId(),
                UUID.randomUUID(),
                OperationType.UPDATE_NODE_CONTENT,
                node.getId(),
                new UpdateNodeContentPayload(
                        new FileContent("path", "subpath")),
                1L);

        assertThrows(
                UnsupportedOperationException.class,
                () -> new CanvasOperationApplier().apply(canvas, operation));
    }

    @Test
    void shouldRejectUpdatingContentOfNonexistentNode() {
        Canvas canvas = new Canvas(UUID.randomUUID(), "test canvas");

        Operation operation = new Operation(
            UUID.randomUUID(),
            canvas.getId(),
            UUID.randomUUID(),
            OperationType.UPDATE_NODE_CONTENT,
            UUID.randomUUID(),
            new UpdateNodeContentPayload(
                new TextContent("does not work")
            ),
            1L
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new CanvasOperationApplier().apply(canvas, operation)
        );
    }

    // TODO: Add tests for various combinations of Operations:
    // shouldRejectNullCanvas
    // shouldRejectNullUserId,
    // shouldRejectMoveNodeWithWrongPayload
}
