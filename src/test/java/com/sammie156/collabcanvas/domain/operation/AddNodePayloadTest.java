package com.sammie156.collabcanvas.domain.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.sammie156.collabcanvas.domain.canvas.CanvasColor;
import com.sammie156.collabcanvas.domain.canvas.Position;
import com.sammie156.collabcanvas.domain.canvas.Size;
import com.sammie156.collabcanvas.domain.canvas.TextNode;
import com.sammie156.collabcanvas.domain.canvas.Node;

public class AddNodePayloadTest {
    @Test
    void shouldStoreNode() {
        UUID id = UUID.randomUUID();

        Node node = new TextNode(
            id,
            new Position(200, 300),
            new Size(10, 14),
            new CanvasColor("3"),
            "3"
        );

        AddNodePayload payload = new AddNodePayload(node);

        assertEquals(id, payload.getNode().getId());
    }
}
