package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

public abstract class Node {
    private final UUID id;
    private Position position;
    private Size size;
    private CanvasColor nodeColor;

    protected Node(
        UUID id,
        Position position,
        Size size,
        CanvasColor canvasColor
    ) {
        this.id = id;
        this.position = position;
        this.size = size;
        this.nodeColor = canvasColor;
    }

    public UUID getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    public CanvasColor getNodeColor() {
        return nodeColor;
    }

    public void moveTo(int x, int y) {
        this.position = new Position(x, y);
    }

    public void resizeTo(int width, int height) {
        this.size = new Size(width, height);
    }

    public void changeColor(CanvasColor color) {
        this.nodeColor = color;
    }
}
