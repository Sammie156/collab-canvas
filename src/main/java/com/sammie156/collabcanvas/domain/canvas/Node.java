package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

public abstract class Node {
    private final UUID uuid;
    private Position position;
    private Size size;
    private CanvasColor nodeColor;

    protected Node(
        UUID id,
        Position position,
        Size size,
        CanvasColor canvasColor
    ) {
        this.uuid = id;
        this.position = position;
        this.size = size;
        this.nodeColor = canvasColor;
    }

    public UUID getId() {
        return uuid;
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
