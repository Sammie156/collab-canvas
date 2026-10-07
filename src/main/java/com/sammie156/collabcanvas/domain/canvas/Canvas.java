package com.sammie156.collabcanvas.domain.canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Canvas {
    private final UUID id;
    private String name;

    private final List<Node> nodes;
    private final List<Edge> edges;

    public Canvas(UUID id, String name) {
        this.id = id;
        this.name = name;

        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Node> getNodes() {
        return List.copyOf(nodes); // so that the list is unmodifiable
    }

    public List<Edge> getEdges() {
        return List.copyOf(edges); // so that the list is unmodifiable
    }
}
