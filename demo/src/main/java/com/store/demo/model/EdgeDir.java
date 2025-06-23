package com.store.demo.model;

public class EdgeDir<E> {
    private VertexDir<E> refDest;
    private int weight;

    public EdgeDir(VertexDir<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    public VertexDir<E> getRefDest() {
        return refDest;
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
            return this.refDest.equals(e.refDest);
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + refDest.getData() + ", peso=" + weight + ")";
    }
}
