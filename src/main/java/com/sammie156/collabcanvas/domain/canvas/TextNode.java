package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

public class TextNode extends Node {
    private String text;

    public TextNode(
            UUID id,
            Position position,
            Size size,
            CanvasColor color,
            String text) {
        super(id, position, size, color);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
