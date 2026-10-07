package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class TextNodeTest {
    private TextNode createTextNode() {
        return new TextNode(
            UUID.randomUUID(),
            new Position(100, 200),
            new Size(10, 25),
            new CanvasColor("4"),
            "Hello World"
        );
    }

    @Test
    void shouldCreateTextNode() {
        UUID id = UUID.randomUUID();

        Position position = new Position(100, 200);
        Size size = new Size(10, 25);
        CanvasColor color = new CanvasColor("2");

        TextNode node = new TextNode(id, position, size, color, "Hello World");

        assertEquals(id, node.getUuid());
        assertEquals(position, node.getPosition());
        assertEquals(size, node.getSize());
        assertEquals(color, node.getNodeColor());
        assertEquals("Hello World", node.getText());
    }

    @Test 
    void shouldMoveNode() {
        TextNode node = createTextNode();

        node.moveTo(400, -200);

        assertEquals(new Position(400, -200), node.getPosition());
    }

    @Test
    void shouldResizeNode() {
        TextNode node = createTextNode();

        node.resizeTo(200, 10);

        assertEquals(new Size(200, 10), node.getSize());
    }

    @Test
    void shouldRecolorNode() {
        TextNode node = createTextNode();

        node.changeColor(new CanvasColor("2"));
        
        assertEquals(new CanvasColor("2"), node.getNodeColor());
    }
}
