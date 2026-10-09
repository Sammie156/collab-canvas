package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;


public class OperationTest {
    @Test
    void shouldStoreOperationMetadata() {
        UUID id = UUID.randomUUID();
        UUID canvas = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();

        Operation operation = new Operation(
            id,
            canvas,
            userId,
            OperationType.ADD_NODE,
            targetId,
            null,
            1L
        );

        assertEquals(id, operation.getId());
        assertEquals(canvas, operation.getCanvasId());
        assertEquals(userId, operation.getUserId());
        assertEquals(OperationType.ADD_NODE, operation.getType());
        assertEquals(targetId, operation.getTargetId());
        assertEquals(null, operation.getPayload());
        assertEquals(1L, operation.getServerRevision());
    }

    @Test
    void shouldAllowZeroRevision() {
        Operation operation = new Operation(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            OperationType.ADD_NODE,
            UUID.randomUUID(),
            null,
            0L
        );

        assertEquals(0L, operation.getServerRevision());
    }

    
}
