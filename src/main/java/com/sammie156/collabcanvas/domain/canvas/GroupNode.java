package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

public class GroupNode extends Node {
    private String label;
    private String background;
    private String backgroundStyle;

    public GroupNode(
            UUID uuid,
            Position position,
            Size size,
            CanvasColor color,
            String label,
            String background,
            String backgroundStyle) {
        super(uuid, position, size, color);

        this.label = label;
        this.background = background;
        this.backgroundStyle = backgroundStyle;
    }

    public String getLabel() {
        return label;
    }

    public String getBackground() {
        return background;
    }

    public String getBackgroundStyle() {
        return backgroundStyle;
    }
}
