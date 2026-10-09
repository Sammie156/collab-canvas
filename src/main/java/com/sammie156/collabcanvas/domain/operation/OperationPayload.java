package com.sammie156.collabcanvas.domain.operation;

public sealed interface OperationPayload 
        permits AddNodePayload,
                MoveNodePayload,
                ResizeNodePayload,
                ChangeNodeColorPayload,
                AddEdgePayload{
    
}
