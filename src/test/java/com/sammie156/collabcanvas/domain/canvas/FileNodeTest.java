package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class FileNodeTest {
    FileNode createFileNode() {
        return new FileNode(
            UUID.randomUUID(),
            new Position(100, 200),
            new Size(10, 15),
            new CanvasColor("4"),
            "..",
            null
        );
    }

    @Test
    void shouldCreateFileNode() {
        UUID uuid = UUID.randomUUID();

        Position position = new Position(100, 200);
        Size size = new Size(10, 15);
        CanvasColor color = new CanvasColor("3");

        String file = "path/to/dummy";
        String subpath = "..";

        FileNode node = new FileNode(uuid, position, size, color, file, subpath);

        assertEquals(uuid, node.getId());
        assertEquals(position, node.getPosition());
        assertEquals(size, node.getSize());
        assertEquals(color, node.getNodeColor());
        assertEquals(file, node.getFile());
        assertEquals(subpath, node.getSubpath());
    }

    @Test 
    void shouldMoveNode() {
        FileNode node = createFileNode();

        node.moveTo(400, -200);

        assertEquals(new Position(400, -200), node.getPosition());
    }

    @Test
    void shouldResizeNode() {
        FileNode node = createFileNode();

        node.resizeTo(200, 10);

        assertEquals(new Size(200, 10), node.getSize());
    }

    @Test
    void shouldRecolorNode() {
        FileNode node = createFileNode();

        node.changeColor(new CanvasColor("2"));
        
        assertEquals(new CanvasColor("2"), node.getNodeColor());
    }
}
