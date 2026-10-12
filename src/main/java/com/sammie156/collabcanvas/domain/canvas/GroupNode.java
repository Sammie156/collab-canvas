package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

/**
 * Implements a Group type node, that acts as a visual container
 * for nodes inside within it. 
 * Check the full specification here: https://jsoncanvas.org/spec/1.0/
 * <br>
 * 
 * <b>Fields</b><br>
 * <li> label: (optional) A text label for the group
 * <li> background: (optional) A path to a background image
 * <li> backgroundStyle: (optional) Rendering styles of the background image
 * <br>
 * Valid backgroundStyles: {@code cover}, {@code ratio}, {@code repeat}
 */
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
