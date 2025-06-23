package com.store.demo.model;

public class EdgeDir<E> {
    private VertexDir<E> destination;
    private int weight;

    public EdgeDir(VertexDir<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public VertexDir<E> getDestination() {
        return destination;
    }

    public void setDestination(VertexDir<E> destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EdgeDir<?>) {
            EdgeDir<?> e = (EdgeDir<?>) o;
            return this.destination.equals(e.destination);
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + destination.getData() + ", peso=" + weight + ")";
    }
}
