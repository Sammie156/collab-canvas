package com.sammie156.collabcanvas.domain.canvas;

public class CanvasColor {
    private final String value;

    public CanvasColor(String value) {
        if (value == null || !isValid(value)) {
            throw new IllegalArgumentException(
                "Invalid canvas color: " + value
            );
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private boolean isValid(String value) {
        return value.matches("[1-6]")
                || value.matches("#[0-9A-Fa-f]{6}");
    }

}
