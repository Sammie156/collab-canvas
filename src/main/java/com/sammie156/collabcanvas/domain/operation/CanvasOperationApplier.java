package com.sammie156.collabcanvas.domain.operation;

import java.util.UUID;

import com.sammie156.collabcanvas.domain.canvas.Canvas;
import com.sammie156.collabcanvas.domain.canvas.Edge;
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
            case RESIZE_NODE -> resizeNode(canvas, operation);
            case CHANGE_NODE_COLOR -> changeNodeColor(canvas, operation);
            case ADD_EDGE -> addEdge(canvas, operation);
            case REMOVE_EDGE -> removeEdge(canvas, operation);

            default -> throw new UnsupportedOperationException(
                    "Operation type is not supported yet: " + operation.getType());
        }
    }

    private void addNode(Canvas canvas, Operation operation) {
        AddNodePayload payload = (AddNodePayload) operation.getPayload();

        canvas.addNode(payload.getNode());
    }

    private void addEdge(Canvas canvas, Operation operation) {
        AddEdgePayload payload = (AddEdgePayload) operation.getPayload();

        canvas.addEdge(payload.getEdge());
    }

    private void removeNode(Canvas canvas, Operation operation) {
        Node node = findNode(canvas, operation.getTargetId());

        canvas.removeNode(node);
    }

    private void removeEdge(Canvas canvas, Operation operation) {
        Edge edge = findEdge(canvas, operation.getTargetId());

        canvas.removeEdge(edge);
    }

    private void moveNode(Canvas canvas, Operation operation) {
        Node node = findNode(canvas, operation.getTargetId());

        MoveNodePayload payload = (MoveNodePayload) operation.getPayload();

        node.moveTo(payload.getPosition().getX(), payload.getPosition().getY());
    }

    private void resizeNode(Canvas canvas, Operation operation) {
        Node node =  findNode(canvas, operation.getTargetId());

        ResizeNodePayload payload = (ResizeNodePayload) operation.getPayload();

        node.resizeTo(payload.getSize().getWidth(), payload.getSize().getHeight());
    }

    private void changeNodeColor(Canvas canvas, Operation operation) {
        Node node = findNode(canvas, operation.getTargetId());

        ChangeNodeColorPayload payload = (ChangeNodeColorPayload) operation.getPayload();

        node.changeColor(payload.getColor());
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

    private Edge findEdge(Canvas canvas, UUID targetId) {
        return canvas.getEdges()
                .stream()
                .filter(edge -> edge.getId().equals(targetId))
                .findFirst()
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        "Edge not found: " + targetId
                    )
                );
    }
}
