package com.sammie156.collabcanvas.domain.operation;

import com.sammie156.collabcanvas.domain.canvas.Node;

public final class AddNodePayload implements OperationPayload{
    private final Node node;

    public AddNodePayload(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }
}
