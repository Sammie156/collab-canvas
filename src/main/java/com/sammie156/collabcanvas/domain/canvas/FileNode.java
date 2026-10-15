package com.sammie156.collabcanvas.domain.canvas;

public class FileNode extends Node{
    private String file;
    private String subpath;

    public FileNode(
        String uuid,
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

    public void updateFile(String file, String subpath) {
        this.file = java.util.Objects.requireNonNull(file, "File cannot be null");
        this.subpath = subpath;
    }
}
