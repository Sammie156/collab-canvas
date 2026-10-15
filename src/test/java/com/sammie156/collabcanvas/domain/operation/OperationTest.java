package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sammie156.collabcanvas.domain.canvas.CanvasColor;
import com.sammie156.collabcanvas.domain.canvas.Position;
import com.sammie156.collabcanvas.domain.canvas.Size;
import com.sammie156.collabcanvas.domain.canvas.TextNode;

import java.util.UUID;

import org.junit.jupiter.api.Test;


public class OperationTest {
    @Test
    void shouldStoreOperationMetadata() {
        String id = UUID.randomUUID().toString();
        String canvas = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        TextNode node = new TextNode(
            UUID.randomUUID().toString(),
            new Position(100, 200),
            new Size(10, 15),
            new CanvasColor("3"),
            "hello"
        );

        Operation operation = new Operation(
            id,
            canvas,
            userId,
            OperationType.ADD_NODE,
            null,
            new AddNodePayload(node),
            1L
        );

        assertEquals(id, operation.getId());
        assertEquals(canvas, operation.getCanvasId());
        assertEquals(userId, operation.getUserId());
        assertEquals(OperationType.ADD_NODE, operation.getType());
        assertEquals(null, operation.getPayload());
        assertEquals(1L, operation.getServerRevision());
    }

    @Test
    void shouldAllowZeroRevision() {
        Operation operation = new Operation(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            OperationType.ADD_NODE,
            UUID.randomUUID().toString(),
            null,
            0L
        );

        assertEquals(0L, operation.getServerRevision());
    }

    @Test
    void shouldRejectInvalidOperationId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Operation(
                null, 
                UUID.randomUUID().toString(), 
                UUID.randomUUID().toString(), 
                OperationType.REMOVE_EDGE, 
                UUID.randomUUID().toString(), 
                null, 
            1L)
        );
    }

    @Test
    void shouldRejectMissingTargetForMoveNode() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Operation(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                OperationType.MOVE_NODE,
                null,
                null,
                1L
            )
        );
    }
}
