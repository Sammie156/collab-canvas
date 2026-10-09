package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

public class Edge {
    private final UUID id;

    private UUID fromNode;
    private EdgeSide fromSide;
    private EdgeEnd fromEnd;

    private UUID toNode;
    private EdgeSide toSide;
    private EdgeEnd toEnd;

    private CanvasColor edgeColor;
    private String label;

    public Edge(UUID uuid, UUID fromNode, EdgeSide fromSide, EdgeEnd fromEnd, UUID toNode, EdgeSide toSide,
            EdgeEnd toEnd, CanvasColor edgeColor, String label) {
        this.id = uuid;
        this.fromNode = fromNode;
        this.fromSide = fromSide;
        this.fromEnd = fromEnd;
        this.toNode = toNode;
        this.toSide = toSide;
        this.toEnd = toEnd;
        this.edgeColor = edgeColor;
        this.label = label;
    }

    public UUID getId() {
        return id;
    }

    public UUID getFromNode() {
        return fromNode;
    }

    public EdgeSide getFromSide() {
        return fromSide;
    }

    public EdgeEnd getFromEnd() {
        return fromEnd;
    }

    public UUID getToNode() {
        return toNode;
    }

    public EdgeSide getToSide() {
        return toSide;
    }

    public EdgeEnd getToEnd() {
        return toEnd;
    }

    public CanvasColor getEdgeColor() {
        return edgeColor;
    }

    public String getLabel() {
        return label;
    }
}
