package com.store.demo.controller;

import com.store.demo.model.EdgeDTO;
import com.store.demo.model.VertexDTO;
import com.store.demo.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grafo")
public class GraphController {

    @Autowired
    private GraphService graphService;

    // GET - Obtener todos los vértices
    @GetMapping("/vertices")
    public List<String> obtenerVertices() {
        return graphService.obtenerVertices();
    }

    // GET - Mostrar grafo completo
    @GetMapping
    public String mostrarGrafo() {
        return graphService.mostrarGrafo();
    }

    // GET - Obtener peso de una arista
    @GetMapping("/aristas/peso")
    public ResponseEntity<Integer> obtenerPesoArista(@RequestParam String source, @RequestParam String destination) {
        int weight = graphService.obtenerPesoArista(source, destination);
        if (weight == -1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(weight);
    }

    // GET - Obtener grado de salida
    @GetMapping("/vertices/{data}/grado-salida")
    public ResponseEntity<Integer> obtenerGradoSalida(@PathVariable String data) {
        int degree = graphService.obtenerGradoSalida(data);
        if (degree == -1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(degree);
    }

    // GET - Obtener grado de entrada
    @GetMapping("/vertices/{data}/grado-entrada")
    public ResponseEntity<Integer> obtenerGradoEntrada(@PathVariable String data) {
        int degree = graphService.obtenerGradoEntrada(data);
        if (degree == -1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(degree);
    }

    // POST - Agregar vértice
    @PostMapping("/vertices")
    public ResponseEntity<Void> agregarVertice(@RequestBody VertexDTO vertexDTO) {
        graphService.agregarVertice(vertexDTO.getData());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // POST - Agregar arista con peso
    @PostMapping("/aristas")
    public ResponseEntity<Void> agregarArista(@RequestBody EdgeDTO edgeDTO) {
        graphService.agregarArista(edgeDTO.getSource(), edgeDTO.getDestination(), edgeDTO.getWeight());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT - Actualizar vértice
    @PutMapping("/vertices/{oldData}")
    public ResponseEntity<Void> actualizarVertice(@PathVariable String oldData, @RequestBody VertexDTO vertexDTO) {
        graphService.actualizarVertice(oldData, vertexDTO.getData());
        return ResponseEntity.ok().build();
    }

    // PUT - Actualizar peso de arista
    @PutMapping("/aristas/peso")
    public ResponseEntity<Void> actualizarPesoArista(@RequestBody EdgeDTO edgeDTO) {
        graphService.actualizarPesoArista(edgeDTO.getSource(), edgeDTO.getDestination(), edgeDTO.getWeight());
        return ResponseEntity.ok().build();
    }

    // DELETE - Eliminar vértice
    @DeleteMapping("/vertices/{data}")
    public ResponseEntity<Void> eliminarVertice(@PathVariable String data) {
        graphService.eliminarVertice(data);
        return ResponseEntity.noContent().build();
    }

    // DELETE - Eliminar arista
    @DeleteMapping("/aristas")
    public ResponseEntity<Void> eliminarArista(@RequestBody EdgeDTO edgeDTO) {
        graphService.eliminarArista(edgeDTO.getSource(), edgeDTO.getDestination());
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/json")
    public List<Map<String, Object>> obtenerGrafoJson() {
        return graphService.obtenerGrafoComoJson();
}

}
