package com.store.demo.service;

import com.store.demo.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GraphService {
    private final GraphLinkDir<String> graph = new GraphLinkDir<>();

    public List<String> obtenerVertices() {
        return graph.getVertices().stream()
                .map(VertexDir::getData)
                .collect(Collectors.toList());
    }

    public void agregarVertice(String data) {
        graph.insertVertex(data);
    }

    public void agregarArista(String source, String destination, int weight) {
        graph.insertEdge(source, destination, weight);
    }

    public void eliminarVertice(String data) {
        graph.removeVertex(data);
    }

    public void eliminarArista(String source, String destination) {
        graph.removeEdge(source, destination);
    }

    public Map<String, Integer> dijkstra(String start) {
        return graph.dijkstra(start);
    }

    public Map<String, Object> visualGraph() {
        return graph.visualGraph();
    }
}
