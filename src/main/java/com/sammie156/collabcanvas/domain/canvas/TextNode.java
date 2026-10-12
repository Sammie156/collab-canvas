package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

/**
 * This class implements a Node that stores just text. 
 * Check the full specification here: https://jsoncanvas.org/spec/1.0/
 * <br>
 * 
 * <b>Fields:</b> <br>
 * <li> text: Stores the text that is stored inside the Node
 */
public class TextNode extends Node {
    private String text;

    /**
     * Creates a TextNode object, defined by the parameters passed.
     * @param id        Unique ID of the specific Node
     * @param position  Position of the Node
     * @param size      Size of the Node
     * @param color     Color of the Node
     * @param text      Text stored in the Node
     */
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
