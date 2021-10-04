package com.lab1.routing;

import java.util.*;

public class Astar<T> {
    public static<T> Optional<List<T>> findPath(T start, T target, AstarParams<T> params){
        // INIT - create open and visited set, add start node to open set and visited set
        var openSet = new PriorityQueue<RouteNode<T>>();
        var visited = new HashMap<Integer, RouteNode<T>>();

        var initialNode = new RouteNode<>(start, null, 0d, params.getHeuiristicValue(start, target));
        openSet.add(initialNode);
        visited.put(params.getItemHash(initialNode.getCurrent()), initialNode);

        // EVAL - execute search with given params
        while(!openSet.isEmpty()){
            var next = openSet.poll();
            // If found target node - return result
            if(params.isSameNode(next.getCurrent(), target)){
                return Optional.of(createList(next));
            }

            // Else - search among children
            params.getConnections(next.getCurrent()).forEach(connection -> {
                var nextNode = visited.getOrDefault(params.getItemHash(connection), new RouteNode<>(connection));
                visited.put(params.getItemHash(nextNode.getCurrent()), nextNode);

                double score = next.getRouteScore() + params.getNodesDistance(next.getCurrent(), connection);
                if(score < nextNode.getRouteScore()){
                    nextNode.setPrevious(next);
                    nextNode.setRouteScore(score);
                    nextNode.setEstimatedScore(score + params.getHeuiristicValue(connection, target));
                    openSet.add(nextNode);
                }
            });
        }

        return Optional.empty();
    }

    private static<T> List<T> createList(RouteNode<T> finish){
        var list = new ArrayList<T>();
        while(finish != null){
            list.add(finish.getCurrent());
            finish = finish.getPrevious();
        }
        Collections.reverse(list);

        return list;
    }
}
