package com.store.demo.model;

import java.util.ArrayList;
import java.util.List;

public class GraphLinkDir<E> {
    private List<VertexDir<E>> vertices;

    public GraphLinkDir() {
        this.vertices = new ArrayList<>();
    }

    public void insertVertex(E data) {
        if (searchVertex(data) == null) {
            vertices.add(new VertexDir<>(data));
        }
    }

    public void insertEdge(E source, E destination, int weight) {
        VertexDir<E> srcVertex = searchVertex(source);
        VertexDir<E> destVertex = searchVertex(destination);
        
        if (srcVertex != null && destVertex != null) {
            srcVertex.addEdge(destVertex, weight);
        }
    }

    public VertexDir<E> searchVertex(E data) {
        for (VertexDir<E> vertex : vertices) {
            if (vertex.getData().equals(data)) {
                return vertex;
            }
        }
        return null;
    }

    public void removeVertex(E data) {
        VertexDir<E> vertexToRemove = searchVertex(data);
        if (vertexToRemove != null) {
            // Eliminar todas las aristas que apuntan a este vértice
            for (VertexDir<E> vertex : vertices) {
                vertex.removeEdge(vertexToRemove);
            }
            // Eliminar el vértice
            vertices.remove(vertexToRemove);
        }
    }

    public void removeEdge(E source, E destination) {
        VertexDir<E> srcVertex = searchVertex(source);
        VertexDir<E> destVertex = searchVertex(destination);
        
        if (srcVertex != null && destVertex != null) {
            srcVertex.removeEdge(destVertex);
        }
    }

    public int outDegree(E data) {
        VertexDir<E> vertex = searchVertex(data);
        return vertex != null ? vertex.getAdjacencyList().size() : -1;
    }

    public int inDegree(E data) {
        VertexDir<E> targetVertex = searchVertex(data);
        if (targetVertex == null) return -1;

        int count = 0;
        for (VertexDir<E> vertex : vertices) {
            for (EdgeDir<E> edge : vertex.getAdjacencyList()) {
                if (edge.getDestination().equals(targetVertex)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getEdgeWeight(E source, E destination) {
        VertexDir<E> srcVertex = searchVertex(source);
        VertexDir<E> destVertex = searchVertex(destination);
        
        if (srcVertex != null && destVertex != null) {
            for (EdgeDir<E> edge : srcVertex.getAdjacencyList()) {
                if (edge.getDestination().equals(destVertex)) {
                    return edge.getWeight();
                }
            }
        }
        return -1; // No existe la arista
    }

    public List<VertexDir<E>> getVertices() {
        return vertices;
    }

    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (VertexDir<E> vertex : vertices) {
            count += vertex.getAdjacencyList().size();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grafo Dirigido Ponderado:\n");
        for (VertexDir<E> vertex : vertices) {
            sb.append(vertex.toString()).append("\n");
        }
        return sb.toString();
    }
}
