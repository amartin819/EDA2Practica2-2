package material.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jvelez
 * @param <V>
 * @param <E>
 */
public class DeepFirstSearch<V, E> {

    /**
     * Search v2 from v1 using a deep first algorithm in the graph g.
     *
     * @param g graph
     * @param v1 start vertex
     * @param v2 end vertex
     * @return the list of edges from v1 to v2
     */
    public List<Edge<E>> getPath(Graph<V, E> g, Vertex<V> v1, Vertex<V> v2) {
        List<Edge<E>> edgeVisited = new ArrayList<>();

        if (g.vertices().contains(v1) && g.vertices().contains(v2)) {
            Stack<Vertex<V>> vertexStack = new Stack<>();
            List<Vertex<V>> vertexVisited = new ArrayList<>();
            List<Edge<E>> adjList = new ArrayList<>();
            Collection col = new ArrayList<>();

            Vertex<V> vertex;

            vertexStack.push(v1);
            boolean seguir = true;
            while (!vertexStack.isEmpty() && seguir) {
                vertex = vertexStack.peek();
                if (vertex != null) {
                    vertexVisited.add(vertex);// visitado
                    col = g.incidentEdges(vertex); // collection de arcos incidentes en vertex
                    if (vertex.equals(v2)) {
                        seguir = false;
                    } else {
                        int contador = arcosSinVisitar(g, vertex, vertexVisited);
                        if (contador > 0) {
                            for (Object e : col) {
                                ELEdge<E, V> edge = (ELEdge<E, V>) e;
                                adjList.add(edge);
                                if (edge.getStartVertex().equals(vertex)
                                        && !vertexVisited.contains(edge.getEndVertex())) {
                                    //vertexVisited.add(vertex);
                                    vertexStack.push(edge.getEndVertex());
                                }
                            }
                        } else {
                            vertexStack.pop();
                        }
                    }
                }
            }

            for (int i = 0; i < vertexVisited.size(); i++) {
                col = g.incidentEdges(vertexVisited.get(i));
                for (Object e : col) {
                    Edge<E> x = (Edge<E>)e;
                    ELEdge<E, V> edge = (ELEdge<E, V>) e;
                    if((i==vertexVisited.size()-1 && edge.getEndVertex().getValue() == vertexVisited.get(0).getValue())|| 
                        ( i<vertexVisited.size()-1 && edge.getEndVertex().getValue() == vertexVisited.get(i+1).getValue()) 
                        ){
                        //System.out.println(vertexVisited.get(i).getValue() + " " + edge.getEndVertex().getValue() + " " + edge.getValue());
                        edgeVisited.add(edge);
                    }
                }
            }
        }
        for(int i=0; i<edgeVisited.size();i++){
            ELEdge<E, V> edge =(ELEdge<E, V>)edgeVisited.get(i);
            System.out.println(edge.getStartVertex().getValue() + " " + edge.getEndVertex().getValue());
        }
        return edgeVisited;
    }

    private int arcosSinVisitar(Graph<V, E> g, Vertex<V> vertex, List<Vertex<V>> vertexVisited) {
        Collection col = g.incidentEdges(vertex);
        int contador = 0;
        for (Object e : col) {
            ELEdge<E, V> edge = (ELEdge<E, V>) e;
            if (edge.getStartVertex().equals(vertex)
                    && !vertexVisited.contains(edge.getEndVertex())) {
                contador++;
            }
        }
        return contador;
    }
}
