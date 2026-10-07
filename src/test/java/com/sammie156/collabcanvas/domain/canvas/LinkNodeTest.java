package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class LinkNodeTest {
    LinkNode createLinkNode() {
        return new LinkNode(
                UUID.randomUUID(),
                new Position(100, 200),
                new Size(10, 15),
                new CanvasColor("3"),
                "google.com");
    }

    @Test
    void shouldCreateLinkNode() {
        UUID id = UUID.randomUUID();

        Position position = new Position(100, 200);
        Size size = new Size(10, 15);
        CanvasColor color = new CanvasColor("3");

        String url = "google.com";

        LinkNode node = new LinkNode(id, position, size, color, url);

        assertEquals(id, node.getUuid());
        assertEquals(position, node.getPosition());
        assertEquals(size, node.getSize());
        assertEquals(color, node.getNodeColor());
        assertEquals(url, node.getUrl());
    }

    @Test
    void shouldMoveNode() {
        LinkNode node = createLinkNode();

        node.moveTo(400, -200);

        assertEquals(new Position(400, -200), node.getPosition());
    }

    @Test
    void shouldResizeNode() {
        LinkNode node = createLinkNode();

        node.resizeTo(200, 10);

        assertEquals(new Size(200, 10), node.getSize());
    }

    @Test
    void shouldRecolorNode() {
        LinkNode node = createLinkNode();

        node.changeColor(new CanvasColor("2"));

        assertEquals(new CanvasColor("2"), node.getNodeColor());
    }
}
