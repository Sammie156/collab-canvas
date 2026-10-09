package com.sammie156.collabcanvas.domain.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class GroupNodeTest {
    GroupNode createGroupNode() {
        return new GroupNode(
            UUID.randomUUID(),
            new Position(300, 200),
            new Size(10, 15),
            new CanvasColor("3"),
            "label",
            "background",
            "backgroundStyle"
        );
    }

    @Test 
    void shouldCreateGroupNode() {
        UUID id = UUID.randomUUID();

        Position position = new Position(100, 200);
        Size size = new Size(10, 15);
        CanvasColor color = new CanvasColor("3");

        String label = "cool stuff";
        String background = "image";
        String backgroundStyle = "ratio";

        GroupNode node = new GroupNode(
            id, 
            position, 
            size, 
            color, 
            label, 
            background, 
            backgroundStyle
        );

        assertEquals(id, node.getId());
        assertEquals(position, node.getPosition());
        assertEquals(size, node.getSize());
        assertEquals(color, node.getNodeColor());
        assertEquals(label, node.getLabel());
        assertEquals(background, node.getBackground());
        assertEquals(backgroundStyle, node.getBackgroundStyle());
    }

    @Test 
    void shouldMoveNode() {
        GroupNode node = createGroupNode();

        node.moveTo(400, -200);

        assertEquals(new Position(400, -200), node.getPosition());
    }

    @Test
    void shouldResizeNode() {
        GroupNode node = createGroupNode();

        node.resizeTo(200, 10);

        assertEquals(new Size(200, 10), node.getSize());
    }

    @Test
    void shouldRecolorNode() {
        GroupNode node = createGroupNode();

        node.changeColor(new CanvasColor("2"));
        
        assertEquals(new CanvasColor("2"), node.getNodeColor());
    }
}
