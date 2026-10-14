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

        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation ID cannot be null");
        }

        if (targetId == null && type == OperationType.MOVE_NODE) {
            throw new IllegalArgumentException(
                    "Target ID cannot be null for moving a node");
        }

        validate(id, canvasId, userId, type, targetId, payload, serverRevision);

        this.id = id;
        this.canvasId = canvasId;
        this.userId = userId;
        this.type = type;
        this.targetId = targetId;
        this.payload = payload;
        this.serverRevision = serverRevision;
    }

    private void validate(
            UUID id,
            UUID canvasId,
            UUID userId,
            OperationType type,
            UUID targetId,
            OperationPayload payload,
            long serverRevision) {
        if (id == null || canvasId == null || userId == null || type == null) {
            throw new IllegalArgumentException(
                    "Operation metadata must not be null");
        }

        if (serverRevision < 0) {
            throw new IllegalArgumentException(
                    "Server revision cannot be negative");
        }

        boolean valid = switch (type) {
            case ADD_NODE ->
                targetId == null && payload instanceof AddNodePayload;

            case REMOVE_NODE, REMOVE_EDGE ->
                targetId != null && payload == null;

            case MOVE_NODE ->
                targetId != null && payload instanceof MoveNodePayload;

            case RESIZE_NODE ->
                targetId != null && payload instanceof ResizeNodePayload;

            case CHANGE_NODE_COLOR ->
                targetId != null
                        && payload instanceof ChangeNodeColorPayload;

            case ADD_EDGE ->
                targetId == null && payload instanceof AddEdgePayload;

            case UPDATE_NODE_CONTENT ->
                targetId != null
                        && payload instanceof UpdateNodeContentPayload;
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };

        if (!valid) {
            throw new IllegalArgumentException(
                    "Invalid target ID or payload for operation type: " + type);
        }
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
