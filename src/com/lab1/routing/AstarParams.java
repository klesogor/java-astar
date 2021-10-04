package com.lab1.routing;

import java.util.stream.Stream;

public interface AstarParams<T> {
    // Possible moves
    Stream<T> getConnections(T state);
    // Distance between two nodes
    double getNodesDistance(T a, T b);
    // Heuristic function
    double getHeuiristicValue(T node, T target);
    // To mark nodes as visited
    int getItemHash(T node);
    // To check if target node is reached
    boolean isSameNode(T a, T b);
}
