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
            
            Stack<Vertex<V>> pila = new Stack<>();
            Stack<Edge<E>> edgeVisited = new Stack<>();
            List<Vertex> vertVisited = new ArrayList<>();
            Collection listAdj;
            Vertex<V> vaux;
            
            pila.push(v1);
            
            while(!pila.empty()){
                vaux = pila.peek();
                if(!vertVisited.contains(vaux)){//si no esta visitado el vertice
                    vertVisited.add(vaux);
                    
                    for(Edge<E> e: g.incidentEdges(vaux)){
                        if(!edgeVisited.contains(e))
                            edgeVisited.add(e);
                        break;
                    }    
                    
                    Vertex<V> next = g.opposite(v2, edgeVisited.peek());
                    
                    if(!vertVisited.contains(next)){
                        pila.push(next);
                        vertVisited.add(next);
                    }else{
                        vaux = g.opposite(vaux, e);
                        if(!pila.contains(vaux) && !vertVisited.contains(vaux)){
                            pila.push(vaux);
                        }
                    }
                }else{
                    pila.pop();
                }
            }
                
                
            
        }
        return null;
    }
    
    private ELEdge<E,V> checkEdge(Edge<E> edge) {
        if (edge instanceof ELEdge){
            ELEdge<E, V> e = (ELEdge<E,V>)edge;
            if (e.getGraph() == this)
                return e;
        }
        throw new RuntimeException("The edge is not in the graph");
    }

    private ELVertex<V> checkVertex(Vertex<V> vertex) {
        if (vertex instanceof ELVertex){
            ELVertex<V> v = (ELVertex<V>)vertex;
            if (v.getGraph() == this)
                return v;
        }
        throw new RuntimeException("The vertex is not in the graph");        
    }
}
