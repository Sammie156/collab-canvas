package com.sammie156.collabcanvas.domain.operation;

import com.sammie156.collabcanvas.domain.canvas.Size;

public final class ResizeNodePayload implements OperationPayload{
    private final Size size;

    public ResizeNodePayload(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }
}
