package com.store.demo.service;

import com.store.demo.model.Vertex;
import com.store.demo.model.Edge;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GraphService {
    private final Map<String, Vertex> vertices = new HashMap<>();
    private final List<Edge> edges = new ArrayList<>();

    // Agregar un vértice (si no existe)
    public Vertex addVertex(String id) {
        return vertices.computeIfAbsent(id, Vertex::new);
    }

    // Obtener todos los vértices
    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    // Agregar una arista dirigida y ponderada
    public void addEdge(String sourceId, String destinationId, double weight) {
        Vertex source = addVertex(sourceId);
        Vertex destination = addVertex(destinationId);
        edges.add(new Edge(source, destination, weight));
    }

    // Obtener todas las aristas
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    // Obtener aristas salientes desde un vértice
    public List<Edge> getEdgesFrom(String sourceId) {
        Vertex source = vertices.get(sourceId);
        if (source == null) return Collections.emptyList();
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                result.add(edge);
            }
        }
        return result;
    }
}
