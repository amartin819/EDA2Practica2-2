package material.graphs;

import java.util.*;


/**
 * Graph implemented as a edge list
 * @author jvelez
 * @param <V>
 * @param <E>
 */
public class ELDigraph <V,E> implements Digraph <V,E> {

    private final Set <ELDiVertex<V>> vertexList = new HashSet<>();
    private final Set <ELEdge<E,V>> edgeList = new HashSet<>();
    
    
    @Override
    public Collection<? extends Vertex<V>> vertices() {
        return Collections.unmodifiableCollection(vertexList);
    }

    @Override
    public Collection<? extends Edge<E>> edges() {
        return Collections.unmodifiableCollection(edgeList);
    }

    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) {
        HashSet <Edge <E> > incidentEdges = new HashSet<>();
        for (ELEdge <E,V> e : edgeList){
            /*if (e.getStartVertex() == v)
                incidentEdges.add(e);*/
            if (e.getEndVertex() == v)
                incidentEdges.add(e);            
        }
        return incidentEdges;    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        ELEdge<E,V> elv = checkEdge(e);
        
        if (elv.getStartVertex() == v)
            return elv.getEndVertex();
        else if (elv.getEndVertex() == v)
            return elv.getStartVertex();
        else
            throw new RuntimeException("The vertex is not in the edge");
        }

    /*NO LO HE TOCADO PERO CREO QUE ESTA BIEN*/
    /************************************************
     ************************************************
     */
    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) {
        ELEdge<E,V> elv = checkEdge(edge);
        ArrayList <Vertex<V> > output = new ArrayList<>();
        output.add(elv.getStartVertex());
        output.add(elv.getEndVertex());
        return output;
        }

    /************************CREO QUE ESTABIEN************************/
    /*RECORRO LA LISTA DE ARISTAS Y MIRO CUAL DE ELLAS TIENE COMO INICIO A START
     *    Y COMO FINAL A END
     */
    @Override
    public boolean areAdjacent(Vertex<V> start, Vertex<V> end) {
        for (ELEdge edge : edgeList){
            if ((edge.getStartVertex() == start) && (edge.getEndVertex() == end))
                return true;
        }
        return false;
    }

    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        ELVertex<V> v = checkVertex(vertex);
        V aux = vertex.getValue();
        v.setValue(vertexValue);
        return aux;    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
         ELEdge<E,V> e = checkEdge(edge);
        E aux = edge.getValue();
        e.setValue(edgeValue);
        return aux;
    }

    /*@Override
    public Vertex<V> insertVertex(V value) {
        ELDiVertex<V> v = new ELDiVertex<>(value,this);
        vertexList.add(v);
        return v;
    }
*/
    @Override
    public Vertex<V> insertVertex(V value) {
        ELDiVertex<V> start = new ELDiVertex<>(value,this);
        //ELDiVertex<V> end = new ELDiVertex<>(value2,this);
        vertexList.add(start);
       // vertexList.add(end);
        return start;
    }
    
    @Override
    public Edge<E> insertEdge(Vertex<V> start, Vertex<V> end, E edgeValue) {
        if (!vertexList.contains(start))
            throw new RuntimeException("The vertex start doesn't belong to this graph");
        if (!vertexList.contains(end))
            throw new RuntimeException("The vertex end doesn't belong to this graph");

        ELEdge<E,V> e = new ELEdge<>(edgeValue,checkVertex(start),checkVertex(end),this);

        if (edgeList.contains(e))
            edgeList.remove(e);
        edgeList.add(e);
        return e;
    }

    /***************CREO QUE ESTA BIEN*************/
    // RECORRO LA LISTA DE ARISITAS Y MIRO CUAL DE ELLAS TIENE
    // COMO VERTICE INICIAL EL VERTICE QUE ME PASAN
    @Override
    public List<Edge<E>> outputEdges(Vertex<V> v) {
        ArrayList<Edge<E>> list = new ArrayList<>();
        ELVertex<V> vert = checkVertex(v);
        
        for (ELEdge <E,V> e : edgeList){
            if(e.getStartVertex()==vert)
                list.add(e);
        }
        
        return list;        
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        ELVertex<V> v = checkVertex(vertex);
        V aux = vertex.getValue();
        vertexList.remove(v);

        //You need an aux set, because you can't remove from a set while you iterate it
        List <ELEdge<E,V>> removeEdgeList = new ArrayList<>();
        for (ELEdge edge : edgeList) {
            if ((edge.getStartVertex() == vertex) || (edge.getEndVertex() == vertex)) {
                removeEdgeList.add(edge);
            }
        }
        
        for (ELEdge edge : removeEdgeList) {
            edgeList.remove(edge);
        }
        
        return aux;
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        ELEdge<E, V> e = checkEdge(edge);
        E aux = edge.getValue();
        edgeList.remove(e);
        return aux;
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
     
    class ELDiVertex <V> implements Vertex <V> {
    private V vertexValue;
    private final Digraph graph;
    
    @Override
    public V getValue() {
        return vertexValue;
    }
    
    public void setValue(V value) {
        vertexValue = value;
    }
    
    public ELDiVertex(V value, Digraph graph) {
        vertexValue = value;
        this.graph = graph;
    }

    /**
     * @return the graph
     */
    public Digraph getGraph() {
        return graph;
    }
}

    class ELEdge <E,V> implements Edge <E> {
        private E edgeValue;
        private final Digraph graph;

        private final Vertex <V> startVertex;
        private final Vertex <V> endVertex;

        @Override
        public int hashCode() {
            int hash = 3;

            final int min = Math.min(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));
            final int max = Math.max(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));

            hash = 67 * hash + Objects.hashCode(this.getGraph());
            hash = 67 * hash + min;
            hash = 67 * hash + max;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ELEdge<E, V> other = (ELEdge<E, V>) obj;
            if (!Objects.equals(this.graph, other.graph)) {
                return false;
            }

            final int min1 = Math.min(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));
            final int max1 = Math.max(Objects.hashCode(this.startVertex), Objects.hashCode(this.endVertex));

            final int min2 = Math.min(Objects.hashCode(other.startVertex), Objects.hashCode(other.endVertex));
            final int max2 = Math.max(Objects.hashCode(other.startVertex), Objects.hashCode(other.endVertex));

            if (!Objects.equals(min1, min2)) {
                return false;
            }
            if (!Objects.equals(max1, max2)) {
                return false;
            }
            return true;
        }

        @Override
        public E getValue() {
            return edgeValue;
        }

        public ELEdge(E value,Vertex<V> startVertex, Vertex<V> endVertex, Digraph graph) {
            edgeValue = value;
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.graph = graph;
        }

        public void setValue(E value) {
            edgeValue = value;
        }

        /**
         * @return the startVertex
         */
        public Vertex <V> getStartVertex() {
            return startVertex;
        }

        /**
         * @return the endVertex
         */
        public Vertex <V> getEndVertex() {
            return endVertex;
        }

        /**
         * @return the graph
         */
        public Digraph getGraph() {
            return graph;
        }

    }
}
