package com.store.demo.service;

import com.store.demo.model.GraphLinkDir;
import com.store.demo.model.VertexDir;
import com.store.demo.model.EdgeDir;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    public void actualizarVertice(String oldData, String newData) {
        VertexDir<String> vertex = graph.searchVertex(oldData);
        if (vertex != null) {
            vertex.setData(newData);
        }
    }

    public void actualizarPesoArista(String source, String destination, int newWeight) {
        VertexDir<String> srcVertex = graph.searchVertex(source);
        VertexDir<String> destVertex = graph.searchVertex(destination);
        
        if (srcVertex != null && destVertex != null) {
            for (EdgeDir<String> edge : srcVertex.getAdjacencyList()) {
                if (edge.getDestination().equals(destVertex)) {
                    edge.setWeight(newWeight);
                    break;
                }
            }
        }
    }

    public int obtenerPesoArista(String source, String destination) {
        return graph.getEdgeWeight(source, destination);
    }

    public String mostrarGrafo() {
        return graph.toString();
    }

    public int obtenerGradoSalida(String data) {
        return graph.outDegree(data);
    }

    public int obtenerGradoEntrada(String data) {
        return graph.inDegree(data);
    }
    public List<Map<String, Object>> obtenerGrafoComoJson() {
    List<Map<String, Object>> grafoJson = new ArrayList<>();
    for (VertexDir<String> vertex : graph.getVertices()) {
        Map<String, Object> verticeMap = new HashMap<>();
        verticeMap.put("vertex", vertex.getData());
        List<Map<String, Object>> edgesList = new ArrayList<>();
        for (EdgeDir<String> edge : vertex.getAdjacencyList()) {
            Map<String, Object> edgeMap = new HashMap<>();
            edgeMap.put("destination", edge.getDestination().getData());
            edgeMap.put("weight", edge.getWeight());
            edgesList.add(edgeMap);
        }
        verticeMap.put("edges", edgesList);
        grafoJson.add(verticeMap);
    }
    return grafoJson;
}

}
