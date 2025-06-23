package com.store.demo.model;

import java.util.ArrayList;
import java.util.List;

public class VertexDir<E> {
    private E data;
    private List<EdgeDir<E>> adjacencyList;

    public VertexDir(E data) {
        this.data = data;
        this.adjacencyList = new ArrayList<>();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public List<EdgeDir<E>> getAdjacencyList() {
        return adjacencyList;
    }

    public void addEdge(VertexDir<E> destination, int weight) {
        EdgeDir<E> edge = new EdgeDir<>(destination, weight);
        if (!adjacencyList.contains(edge)) {
            adjacencyList.add(edge);
        }
    }

    public void removeEdge(VertexDir<E> destination) {
        adjacencyList.removeIf(edge -> edge.getRefDest().equals(destination));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VertexDir<?>) {
            VertexDir<?> v = (VertexDir<?>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    @Override
    public String toString() {
        return data + " --> " + adjacencyList.toString();
    }
}
