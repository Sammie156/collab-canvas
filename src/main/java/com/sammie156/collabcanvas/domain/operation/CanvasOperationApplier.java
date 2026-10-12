package com.sammie156.collabcanvas.domain.operation;

import java.util.UUID;

import com.sammie156.collabcanvas.domain.canvas.Canvas;
import com.sammie156.collabcanvas.domain.canvas.Node;

public class CanvasOperationApplier {
    public void apply(Canvas canvas, Operation operation) {
        if (canvas == null || operation == null) {
            throw new IllegalArgumentException(
                    "Canvas and Operation must not be null");
        }

        if (!canvas.getId().equals(operation.getCanvasId())) {
            throw new IllegalArgumentException(
                    "Operation belongs to a different canvas");
        }

        switch (operation.getType()) {
            case ADD_NODE -> addNode(canvas, operation);
            case REMOVE_NODE -> removeNode(canvas, operation);
            case MOVE_NODE -> moveNode(canvas, operation);

            default -> throw new UnsupportedOperationException(
                    "Operation type is not supported yet: " + operation.getType());
        }
    }

    private void addNode(Canvas canvas, Operation operation) {
        AddNodePayload payload = (AddNodePayload) operation.getPayload();

        canvas.addNode(payload.getNode());
    }

    private void removeNode(Canvas canvas, Operation operation) {
        Node node = findNode(canvas, operation.getTargetId());

        canvas.removeNode(node);
    }

    private void moveNode(Canvas canvas, Operation operation) {
        Node node = findNode(canvas, operation.getTargetId());

        MoveNodePayload payload = (MoveNodePayload) operation.getPayload();

        node.moveTo(payload.getPosition().getX(), payload.getPosition().getY());
    }

    private Node findNode(Canvas canvas, UUID targetId) {
        return canvas.getNodes()
                .stream()
                .filter(node -> node.getId().equals(targetId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Node not found: " + targetId));
    }
}
