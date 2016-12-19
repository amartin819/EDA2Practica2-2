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
public class DeepFirstSearch <V,E> {
    /**
     * Search v2 from v1 using a deep first algorithm in the graph g.
     * @param g graph
     * @param v1 start vertex
     * @param v2 end vertex
     * @return the list of edges from v1 to v2
     */
    public List <Edge <E>> getPath(Graph <V,E> g, Vertex <V> v1, Vertex <V> v2) {
        if(g.vertices().contains(v1)&& g.vertices().contains(v2)){
            
            Stack<Vertex<V>> pilaVertex = new Stack<>();
            Stack<Edge<E>> pilaEdge = new Stack<>();
            List<Vertex<V>> vertVisited = new ArrayList<>();
            List<Edge<E>> edgeVisited = new ArrayList<>();
            List<Edge<E>> adjList = new ArrayList<>();
            Vertex<V> vaux;
            
            pilaVertex.push(v1);
            vertVisited.add(v1);
            
            while(!pilaVertex.isEmpty()){
                vaux = pilaVertex.peek();
                adjList.add((Edge<E>) g.incidentEdges(vaux));
                for(Edge<E> e : adjList){
                    pilaEdge.push(e);
                }
                while(!pilaEdge.isEmpty()){
                    edgeVisited.add(pilaEdge.peek());
                    if(v2 == g.opposite(vaux, pilaEdge.pop())){
                        return edgeVisited;
                    }else{
                        vaux = g.opposite(vaux, pilaEdge.peek());

                    }
                }
            }
        }else
            throw new RuntimeException("The vertex are not in this graph");
        return null;
    }
}