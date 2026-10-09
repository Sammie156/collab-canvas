package com.sammie156.collabcanvas.domain.operation;

import com.sammie156.collabcanvas.domain.canvas.Edge;

public final class AddEdgePayload implements OperationPayload{
    private final Edge edge;

    public AddEdgePayload(Edge edge) {
        this.edge = edge;
    }

    public Edge getEdge() {
        return edge;
    }
}
