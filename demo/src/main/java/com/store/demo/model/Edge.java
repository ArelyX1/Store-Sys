package com.store.demo.model;

// Representa una arista dirigida y ponderada
public class Edge {
    private final Vertex source;
    private final Vertex destination;
    private final double weight;

    public Edge(Vertex source, Vertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + " [peso=" + weight + "]";
    }
}
