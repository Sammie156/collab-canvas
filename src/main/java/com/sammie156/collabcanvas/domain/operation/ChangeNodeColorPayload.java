package com.sammie156.collabcanvas.domain.operation;

import com.sammie156.collabcanvas.domain.canvas.CanvasColor;

public final class ChangeNodeColorPayload implements OperationPayload{
    private final CanvasColor color;

    public ChangeNodeColorPayload(CanvasColor color) {
        this.color = color;
    }

    public CanvasColor getColor() {
        return color;
    }
}
