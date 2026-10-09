package com.sammie156.collabcanvas.domain.operation;

import com.sammie156.collabcanvas.domain.canvas.Position;

public final class MoveNodePayload implements OperationPayload{
    private final Position position;

    public MoveNodePayload(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
