package com.store.demo.controller;

import com.store.demo.model.EdgeDTO;
import com.store.demo.model.VertexDTO;
import com.store.demo.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grafo")
public class GraphController {

    @Autowired
    private GraphService graphService;

    @GetMapping("/vertices")
    public List<String> obtenerVertices() {
        return graphService.obtenerVertices();
    }

    @PostMapping("/vertices")
    public void agregarVertice(@RequestBody VertexDTO vertexDTO) {
        graphService.agregarVertice(vertexDTO.getData());
    }

    @PostMapping("/aristas")
    public void agregarArista(@RequestBody EdgeDTO edgeDTO) {
        graphService.agregarArista(edgeDTO.getSource(), edgeDTO.getDestination(), edgeDTO.getWeight());
    }

    @DeleteMapping("/vertices/{data}")
    public void eliminarVertice(@PathVariable String data) {
        graphService.eliminarVertice(data);
    }

    @DeleteMapping("/aristas")
    public void eliminarArista(@RequestBody EdgeDTO edgeDTO) {
        graphService.eliminarArista(edgeDTO.getSource(), edgeDTO.getDestination());
    }

    @GetMapping("/dijkstra")
    public Map<String, Integer> dijkstra(@RequestParam String start) {
        return graphService.dijkstra(start);
    }

    @GetMapping("/visual")
    public Map<String, Object> visualGraph() {
        return graphService.visualGraph();
    }
}
