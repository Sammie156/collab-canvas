package com.sammie156.collabcanvas.domain.operation;

import java.util.UUID;

public class Operation {
    private final UUID id;
    private final UUID canvasId;
    private final UUID userId;
    private final OperationType type;
    private final UUID targetId;
    private final OperationPayload payload;
    private final long serverRevision;

    public Operation(
            UUID id,
            UUID canvasId,
            UUID userId,
            OperationType type,
            UUID targetId,
            OperationPayload payload,
            long serverRevision) {
        this.id = id;
        this.canvasId = canvasId;
        this.userId = userId;
        this.type = type;
        this.targetId = targetId;
        this.payload = payload;
        this.serverRevision = serverRevision;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCanvasId() {
        return canvasId;
    }

    public UUID getUserId() {
        return userId;
    }

    public OperationType getType() {
        return type;
    }

    public UUID getTargetId() {
        return targetId;
    }

    public OperationPayload getPayload() {
        return payload;
    }

    public long getServerRevision() {
        return serverRevision;
    }
}
