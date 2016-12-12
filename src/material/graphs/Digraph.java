/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material.graphs;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author jvelez
 */
interface Digraph<V, E> {
    /**
     * @return all vertices of the graph.
     */
    Collection <? extends Vertex <V> > vertices();
        
    /**
     * @return all the edges of the graph.
     */
    Collection <? extends Edge <E> > edges();

    /**
     * @return an iterable collection of the edges incident upon vertex v.
     */
    Collection <? extends Edge <E> > incidentEdges(Vertex <V> v);
    
    /**
     * @return the end vertex of the edge e distinct of e.
     */
    Vertex <V> opposite(Vertex <V> v, Edge <E> e);

    /**
     * @return an array storing the end vertices of edge.
     */
    List <Vertex <V> > endVertices(Edge <E> edge);
        
    /**
     * Test whether vertices v1 and v2 are adjacent.
     * @return true if are adjacent
     */
    boolean areAdjacent(Vertex <V> v1, Vertex <V> v2);

    /**
     * Replace the element stored at vertex with vertexValue.
     * @return the old element stored at vertex.
     */
    V replace(Vertex <V> vertex, V vertexValue);
    
    /**
     * Replace the element stored at edge with edgeValue.
     * @return the old element stored at edge.
     */
    E replace(Edge <E> edge, E edgeValue);
            
    /**
     * Insert and return a new vertex storing element value.
     */
    Vertex <V> insertVertex(V value);

    /**
     * Insert and return a new undirected edge with end vertices 
     * v1 and v2 and storing element vertexValue.
     * If already exists an edge with this vertices the edge is replaced.
     */
    Edge <E> insertEdge(Vertex <V> v1, Vertex <V> v2, E edgeValue);
    
    /**
     * 
     * @param v a vertex
     * @return the output edges of a vertex 
     */
    List<Edge<E>> outputEdges(Vertex<V> v);
    
    /**
     * Remove vertex v and all its incident edges.
     * @return the element stored at vertex
     */
    V removeVertex(Vertex <V> vertex);

    /**
     * Remove edge
     * @return the element stored at e.
     */
    E removeEdge(Edge <E> edge);
}
