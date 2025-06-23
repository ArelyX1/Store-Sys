package com.store.demo.model;

import java.util.*;

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

    // DIJKSTRA
    public Map<E, Integer> dijkstra(E start) {
        Map<E, Integer> distances = new HashMap<>();
        Set<E> visited = new HashSet<>();
        PriorityQueue<Pair<E, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));

        for (VertexDir<E> v : vertices) {
            distances.put(v.getData(), Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.add(new Pair<>(start, 0));

        while (!pq.isEmpty()) {
            Pair<E, Integer> current = pq.poll();
            E node = current.getKey();
            if (visited.contains(node)) continue;
            visited.add(node);

            VertexDir<E> vNode = searchVertex(node);
            if (vNode == null) continue;

            for (EdgeDir<E> edge : vNode.getAdjacencyList()) {
                E neighbor = edge.getRefDest().getData();
                int weight = edge.getWeight();
                int newDist = distances.get(node) + weight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new Pair<>(neighbor, newDist));
                }
            }
        }
        return distances;
    }

    public List<VertexDir<E>> getVertices() {
        return vertices;
    }

    // Para visualización con vis-network
    public Map<String, Object> visualGraph() {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();
        for (VertexDir<E> v : vertices) {
            nodes.add(Map.of("id", v.getData(), "label", v.getData()));
            for (EdgeDir<E> e : v.getAdjacencyList()) {
                edges.add(Map.of(
                    "from", v.getData(),
                    "to", e.getRefDest().getData(),
                    "label", String.valueOf(e.getWeight())
                ));
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("nodes", nodes);
        result.put("edges", edges);
        return result;
    }

    // Clase auxiliar para Dijkstra
    private static class Pair<K, V> {
        private final K key;
        private final V value;
        public Pair(K key, V value) { this.key = key; this.value = value; }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }
}
