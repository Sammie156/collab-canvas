package com.sammie156.collabcanvas.domain.canvas;

import java.util.UUID;

/**
 * This class implements a Node that references other files
 * or attachments, such as images, video, etc. 
 * Check the full specification here: https://jsoncanvas.org/spec/1.0/
 * <br>
 * 
 * <b>Fields</b><br>
 * <li> file: (required) The path to the file being referenced
 * <li> subpath: (optional) A subpath that may link to a heading or a
 *                          block inside the given file
 */
public class FileNode extends Node{
    private String file;
    private String subpath;

    /**
     * Creates a FileNode, defined by the parameters passed.
     * @param id      Unique ID of the specific Node
     * @param position  Position of the Node
     * @param size      Size of the Node
     * @param color     Color of the Node
     * @param file      The path to the file that the Node links
     * @param subpath   Any subpath within the file
     */
    public FileNode(
        UUID id,
        Position position,
        Size size,
        CanvasColor color,
        String file,
        String subpath
    ) {
        super(id, position, size, color);
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
