/*
Copyright 2025 Pablo Lozano Hernández
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific
language governing permissions and limitations under the
License.
*/

package com.aplicacion.pr2;
import java.util.*;

public class Graph<V> {
    // Lista de adyacencia
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice ‘v‘ al grafo.
     * @param v vértice a añadir.
     * @return ‘true‘ si no estaba anteriormente y ‘false‘ en caso contrario.
     */
    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    /**
     * Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo. Si no existen, los añade.
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return ‘true‘ si no existía el arco y ‘false‘ en caso contrario.
     */
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a ‘v‘.
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     * @throws Exception si el vértice no está en el grafo.
     */
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice no está en el grafo");
        }
        return adjacencyList.get(v);
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     * @param v vértice para el que se realiza la comprobación.
     * @return ‘true‘ si ‘v‘ es un vértice del grafo.
     */
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Metodo ‘toString()‘ reescrito para mostrar la lista de adyacencia.
     * @return una cadena de caracteres con la lista de adyacencia.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, Set<V>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de que exista, un camino entre ‘v1‘ y ‘v2‘ usando búsqueda en profundidad (DFS).
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices desde ‘v1‘ hasta ‘v2‘, o null si no hay camino.
     */
    public List<V> onePath(V v1, V v2) {
        if (!containsVertex(v1) || !containsVertex(v2)) {
            return null;
        }
        Map<V, V> trace = new HashMap<>();
        Stack<V> stack = new Stack<>();
        stack.push(v1);
        trace.put(v1, null);

        while (!stack.isEmpty()) {
            V current = stack.pop();
            if (current.equals(v2)) {
                return reconstructPath(trace, v1, v2);
            }
            for (V neighbor : adjacencyList.get(current)) {
                if (!trace.containsKey(neighbor)) {
                    stack.push(neighbor);
                    trace.put(neighbor, current);
                }
            }
        }
        return null;
    }

    /**
     * Reconstruye el camino desde ‘v1‘ hasta ‘v2‘ usando la traza.
     */
    private List<V> reconstructPath(Map<V, V> trace, V start, V end) {
        List<V> path = new LinkedList<>();
        for (V at = end; at != null; at = trace.get(at)) {
            path.add(0, at);
        }
        return path;
    }
}
