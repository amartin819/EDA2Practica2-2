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
        List<Edge<E>> edgeVisited = new ArrayList<>();
        
        if(g.vertices().contains(v1)&& g.vertices().contains(v2)){
            Stack<Vertex<V>> vertexStack = new Stack<>();
            List<Vertex<V>> vertexVisited = new ArrayList<>();
            List<Edge<E>> adjList = new ArrayList<>();
            Collection col = new ArrayList<>();
            
            Vertex<V> vertex;
            
            vertexStack.push(v1);
            vertex = vertexStack.peek();
            
            while(!vertexStack.isEmpty()){
                vertexVisited.add(vertex);
                col = g.incidentEdges(vertex);
                for(Object e : col){
                    adjList.add((Edge<E>) e);
                    if(!vertexVisited.contains(g.opposite(vertex, (Edge<E>) e)) || g.opposite(vertex, (Edge<E>) e)==v2){
                        vertex = g.opposite(vertex, (Edge<E>) e);
                        vertexVisited.add(vertex);
                        vertexStack.push(vertex);
                    }else{
                        vertexStack.pop();
                        //break;
                    }
                }
                
            }
        }
        return edgeVisited;
    }
}