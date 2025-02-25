package com.pr2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;

/**
 * Clase de pruebas unitarias para la clase Graph.
 */
public class GraphTest {

    /**
     * Este test comprueba que el metodo `onePath(V v1, V v2)`
     * encuentra un camino entre `v1` y `v2` cuando existe.
     */
    @Test
    public void onePathFindsAPath(){
        System.out.println("\nTest onePathFindsAPath");
        System.out.println("----------------------");
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);
        assertEquals(expectedPath, g.onePath(1, 4));
    }

    /**
     * Este test comprueba que el metodo `onePath(V v1, V v2)`
     * devuelve null cuando no existe un camino entre `v1` y `v2`.
     */
    @Test
    public void onePathReturnsNullWhenNoPathExists() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        assertNull(g.onePath(1, 4));
    }

    /**
     * Este test comprueba que el metodo `onePath(V v1, V v2)`
     * devuelve null cuando alguno de los vértices no existe.
     */
    @Test
    public void onePathReturnsNullForNonExistentVertices() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        assertNull(g.onePath(1, 5));
    }

    /**
     * Este test comprueba que el metodo `addVertex(V v)`
     * devuelve true cuando se añade un nuevo vértice.
     */
    @Test
    public void addVertexReturnsTrueForNewVertex() {
        Graph<Integer> g = new Graph<>();
        assertTrue(g.addVertex(1));
    }

    /**
     * Este test comprueba que el metodo `addVertex(V v)`
     * devuelve false cuando el vértice ya existe.
     */
    @Test
    public void addVertexReturnsFalseForExistingVertex() {
        Graph<Integer> g = new Graph<>();
        g.addVertex(1);
        assertFalse(g.addVertex(1));
    }

    /**
     * Este test comprueba que el metodo `addEdge(V v1, V v2)`
     * devuelve true cuando se añade una nueva arista.
     */
    @Test
    public void addEdgeReturnsTrueForNewEdge() {
        Graph<Integer> g = new Graph<>();
        assertTrue(g.addEdge(1, 2));
    }

    /**
     * Este test comprueba que el metodo `addEdge(V v1, V v2)`
     * devuelve false cuando la arista ya existe.
     */
    @Test
    public void addEdgeReturnsFalseForExistingEdge() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        assertFalse(g.addEdge(1, 2));
    }

    /**
     * Este test comprueba que el metodo `obtainAdjacents(V v)`
     * devuelve el conjunto correcto de vértices adyacentes.
     */
    @Test
    public void obtainAdjacentsReturnsCorrectSet() throws Exception {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        Set<Integer> adjacents = g.obtainAdjacents(1);
        assertTrue(adjacents.contains(2));
        assertTrue(adjacents.contains(3));
    }

    /**
     * Este test comprueba que el metodo `obtainAdjacents(V v)`
     * lanza una excepción cuando el vértice no existe.
     */
    @Test
    public void obtainAdjacentsThrowsExceptionForNonExistentVertex() {
        Graph<Integer> g = new Graph<>();
        assertThrows(Exception.class, () -> g.obtainAdjacents(1));
    }

    /**
     * Este test comprueba que el metodo `containsVertex(V v)`
     * devuelve true cuando el vértice existe.
     */
    @Test
    public void containsVertexReturnsTrueForExistingVertex() {
        Graph<Integer> g = new Graph<>();
        g.addVertex(1);
        assertTrue(g.containsVertex(1));
    }

    /**
     * Este test comprueba que el metodo `containsVertex(V v)`
     * devuelve false cuando el vértice no existe.
     */
    @Test
    public void containsVertexReturnsFalseForNonExistentVertex() {
        Graph<Integer> g = new Graph<>();
        assertFalse(g.containsVertex(1));
    }
}