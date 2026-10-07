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

    public void addNode(Node node) {
        boolean alreadyExists = nodes.stream().anyMatch(
            existing -> existing.getId().equals(node.getId())
        );

        // We don't want any duplicate nodes
        if (alreadyExists) {
            throw new IllegalArgumentException(
                "Node with id " + node.getId() + " already exists"
            );
        }

        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        boolean fromExists = nodes.stream().anyMatch(
            existing -> existing.getId().equals(edge.getFromNode())
        );

        boolean toExists = nodes.stream().anyMatch(
            existing -> existing.getId().equals(edge.getToNode())
        );

        if (!fromExists || !toExists) {
            throw new IllegalArgumentException(
                "Edge must reference nodes that exist in the canvas"
            );
        }

        boolean alreadyExists = edges.stream().anyMatch(
            existing -> existing.getUuid().equals(edge.getUuid())
        );

        if (alreadyExists) {
            throw new IllegalArgumentException(
                "Edge with id " + edge.getUuid() + " already exists"
            );
        }

        edges.add(edge);
    }

    public void removeNode(Node node) {
        boolean removed = nodes.removeIf(
            existing -> existing.getId().equals(node.getId())
        );

        if (!removed) {
            throw new IllegalArgumentException(
                "Node with id " + node.getId() + " does not exist"
            );
        }

        edges.removeIf(edge -> 
            edge.getFromNode().equals(node.getId()) ||
            edge.getToNode().equals(node.getId())
        );
    }

    public void removeEdge(Edge edge) {
        boolean removed = edges.removeIf(
            existing -> existing.getUuid().equals(edge.getUuid())
        );

        if (!removed) {
            throw new IllegalArgumentException(
                "Edge with id " + edge.getUuid() + " does not exist"
            );
        }
    }
}
