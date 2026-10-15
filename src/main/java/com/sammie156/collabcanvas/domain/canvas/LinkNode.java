package com.sammie156.collabcanvas.domain.canvas;

public class LinkNode extends Node {
    private String url;

    public LinkNode(
            String uuid,
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

    public void updateLink(String url) {
        this.url = java.util.Objects.requireNonNull(url, "Link cannot be null");
    }
}
