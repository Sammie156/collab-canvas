package com.sammie156.collabcanvas.domain.operation;

import java.util.Objects;

public final class UpdateNodeContentPayload implements OperationPayload {
    public sealed interface Content permits TextContent, FileContent, LinkContent, GroupContent {
    }

    public record TextContent(String text) implements Content {
        public TextContent {
            Objects.requireNonNull(text, "Text cannot be null");
        }
    }

    public record FileContent(String file, String subpath) implements Content {
        public FileContent {
            Objects.requireNonNull(file, "File path cannot be null");
        }
    }

    public record LinkContent(String link) implements Content {
        public LinkContent {
            Objects.requireNonNull(link, "Link canno be null");
        }
    }

    public record GroupContent(String label, String background, String backgroundStyle) implements Content {
        // all fields are optional, so... there's nothing
    }

    private final Content content;

    public UpdateNodeContentPayload(Content content) {
        this.content = Objects.requireNonNull(content, "Content cannot be null");
    }

    public Content getContent() {
        return content;
    }
}
