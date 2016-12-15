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
            
            Stack<Edge> pila = new Stack<>();
            List<Edge<E>> listVisited = new ArrayList<>();
            Collection listAdj = g.incidentEdges(v1);
           
            for(Object elem: listAdj){
                pila.add((Edge) elem);
            }
            
            while(!pila.empty()){
                //ELEdge vaux =checkEdge(pila.pop()) ;
                Edge vaux = pila.pop();
                if(vaux!=null /*&& !listVisited.contains(vaux)*/){
                    listVisited.add(vaux);

                    if(vaux.equals(v2)/*vaux.getEndVertex().equals(v2)*/){
                        return listVisited;
                    }
                    else{
                        listAdj = g.incidentEdges((Vertex<V>) vaux);
                        for(Object elem: listAdj)
                            pila.add((Edge) elem);
                    }
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
