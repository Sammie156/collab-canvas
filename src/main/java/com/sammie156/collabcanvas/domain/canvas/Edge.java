package com.sammie156.collabcanvas.domain.canvas;

public class Edge {
    private final String id;

    private String fromNode;
    private EdgeSide fromSide;
    private EdgeEnd fromEnd;

    private String toNode;
    private EdgeSide toSide;
    private EdgeEnd toEnd;

    private CanvasColor edgeColor;
    private String label;

    public Edge(String uuid, String fromNode, EdgeSide fromSide, EdgeEnd fromEnd, String toNode, EdgeSide toSide,
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

    public String getId() {
        return id;
    }

    public String getFromNode() {
        return fromNode;
    }

    public EdgeSide getFromSide() {
        return fromSide;
    }

    public EdgeEnd getFromEnd() {
        return fromEnd;
    }

    public String getToNode() {
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
