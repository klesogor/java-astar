package com.lab1.routing;

public class RouteNode<T> implements Comparable<RouteNode<T>>{
    private RouteNode<T> previous;
    private final T current;
    private double routeScore;
    private double estimatedScore;

    RouteNode(T current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    RouteNode(T current, RouteNode<T> previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    public RouteNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(RouteNode<T> previous) {
        this.previous = previous;
    }

    public T getCurrent() {
        return current;
    }

    public double getRouteScore() {
        return routeScore;
    }

    public void setRouteScore(double routeScore) {
        this.routeScore = routeScore;
    }

    public double getEstimatedScore() {
        return estimatedScore;
    }

    public void setEstimatedScore(double estimatedScore) {
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(RouteNode<T> other) {
        if(this.estimatedScore > other.estimatedScore){
            return 1;
        } else if(this.estimatedScore < other.estimatedScore){
            return -1;
        }

        return 0;
    }
}
