package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

/**
 * This class defines a Node as defined by the Canvas Spec.
 * <br>
 * Check the full specification here: https://jsoncanvas.org/spec/1.0/
 * <br>
 * 
 * This acts as an abstract class so that different types of Nodes 
 * can be implemented as per the specification. This class is 
 * implemented as follows:
 * <ul>
 * <li> TextNode
 * <li> FileNode
 * <li> GroupNode
 * <li> LinkNode
 * </ul>
 */
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
