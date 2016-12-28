/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material.graphs;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jvelez
 */
public class DeepFirstSearchTest {
    
    public DeepFirstSearchTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPath method, of class DeepFirstSearch.
     */
    /*
    @Test
    public void testEasyGetPath() {
        System.out.println("Easy get path");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("A");
        Vertex <String> v2 = graph.insertVertex("B");
        Vertex <String> v3 = graph.insertVertex("C");

        Edge<Integer> e1 = graph.insertEdge(v1, v2, 2);
        Edge<Integer> e2 = graph.insertEdge(v2, v3, 4);
        
        DeepFirstSearch b = new DeepFirstSearch();
        List<Edge> l = b.getPath(graph, v1,v3);
        
        assertEquals(l.get(0),e1);
        assertEquals(l.get(1),e2);
    }
*/
    /**
     * Test of getPath method, of class DeepFirstSearch.
     */
    @Test
    public void testGetPath() {
        System.out.println("get path");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("A");
        Vertex <String> v2 = graph.insertVertex("B");
        Vertex <String> v3 = graph.insertVertex("C");
        Vertex <String> v4 = graph.insertVertex("D");

        Edge<Integer> e1 = graph.insertEdge(v1, v2, 1);//2
        Edge<Integer> e2 = graph.insertEdge(v1, v3, 2);//4
        Edge<Integer> e3 = graph.insertEdge(v3, v4, 3);//4
        Edge<Integer> e4 = graph.insertEdge(v4, v1, 4);//4
        
        DeepFirstSearch b = new DeepFirstSearch();
        List<Edge> l = b.getPath(graph, v1,v4);
        
        assertEquals(l.get(0).getValue(),e2.getValue());
        assertEquals(l.get(1).getValue(),e3.getValue());
        assertEquals(l.get(2).getValue(),e4.getValue());
        System.out.println("he acabado");
    }

}
