package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

public class LinkNode extends Node {
    private String url;

    public LinkNode(
            UUID uuid,
            Position position,
            Size size,
            CanvasColor color,
            String url) {
        super(uuid, position, size, color);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
