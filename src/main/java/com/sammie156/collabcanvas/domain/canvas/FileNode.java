package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

public class FileNode extends Node{
    private String file;
    private String subpath;

    public FileNode(
        UUID uuid,
        Position position,
        Size size,
        CanvasColor color,
        String file,
        String subpath
    ) {
        super(uuid, position, size, color);
        this.file = file;
        this.subpath = subpath;
    }

    public String getFile() {
        return file;
    }

    public String getSubpath() {
        return subpath;
    }
}
