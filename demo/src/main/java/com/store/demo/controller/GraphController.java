package com.store.demo.controller;

import com.store.demo.model.Vertex;
import com.store.demo.model.Edge;
import com.store.demo.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para el grafo
@RestController
@RequestMapping("/api/grafo")
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    // Agregar un vértice
    @PostMapping("/vertex")
    public Vertex addVertex(@RequestBody VertexDTO vertexDTO) {
        return graphService.addVertex(vertexDTO.getId());
    }

    // Listar todos los vértices
    @GetMapping("/vertices")
    public List<Vertex> getVertices() {
        return graphService.getVertices();
    }

    // Agregar una arista
    @PostMapping("/edge")
    public void addEdge(@RequestBody EdgeDTO edgeDTO) {
        graphService.addEdge(edgeDTO.getSource(), edgeDTO.getDestination(), edgeDTO.getWeight());
    }

    // Listar todas las aristas
    @GetMapping("/edges")
    public List<Edge> getEdges() {
        return graphService.getEdges();
    }

    // Listar aristas salientes de un vértice
    @GetMapping("/edges/from/{sourceId}")
    public List<Edge> getEdgesFrom(@PathVariable String sourceId) {
        return graphService.getEdgesFrom(sourceId);
    }

    // DTOs para recibir datos en POST
    public static class VertexDTO {
        private String id;
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }

    public static class EdgeDTO {
        private String source;
        private String destination;
        private double weight;

        public String getSource() { return source; }
        public void setSource(String source) { this.source = source; }

        public String getDestination() { return destination; }
        public void setDestination(String destination) { this.destination = destination; }

        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }
    }
}
