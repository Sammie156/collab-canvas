package com.sammie156.collabcanvas.domain.operation;

import com.sammie156.collabcanvas.domain.canvas.Canvas;
import com.sammie156.collabcanvas.domain.canvas.Edge;
import com.sammie156.collabcanvas.domain.canvas.FileNode;
import com.sammie156.collabcanvas.domain.canvas.GroupNode;
import com.sammie156.collabcanvas.domain.canvas.LinkNode;
import com.sammie156.collabcanvas.domain.canvas.Node;
import com.sammie156.collabcanvas.domain.canvas.TextNode;

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
            case UPDATE_NODE_CONTENT -> updateNodeContent(canvas, operation);

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

    private Node findNode(Canvas canvas, String targetId) {
        return canvas.getNodes()
                .stream()
                .filter(node -> node.getId().equals(targetId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Node not found: " + targetId));
    }

    private Edge findEdge(Canvas canvas, String targetId) {
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

    private void updateNodeContent(Canvas canvas, Operation operation) {
        Node node = findNode(canvas, operation.getTargetId());

        UpdateNodeContentPayload payload = (UpdateNodeContentPayload) operation.getPayload();

        UpdateNodeContentPayload.Content content = payload.getContent();

        if (node instanceof TextNode textNode &&
            content instanceof UpdateNodeContentPayload.TextContent text
        ) {
            textNode.updateText(text.text());
        } else if (node instanceof FileNode fileNode &&
            content instanceof UpdateNodeContentPayload.FileContent file
        ) {
            fileNode.updateFile(file.file(), file.subpath());
        } else if (node instanceof LinkNode linkNode &&
            content instanceof UpdateNodeContentPayload.LinkContent link
        ) {
            linkNode.updateLink(link.link());
        } else if (node instanceof GroupNode groupNode &&
            content instanceof UpdateNodeContentPayload.GroupContent group
        ) {
            groupNode.updateGroup(group.label(), group.background(), group.backgroundStyle());
        } else {
            throw new UnsupportedOperationException(
                "Content type does not match Node type: " + node.getClass().getSimpleName()
            );
        }
    }
}
