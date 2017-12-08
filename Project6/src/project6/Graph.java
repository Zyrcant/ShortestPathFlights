/*
 * Name of the Student: Tiffany Do
 * Class: CS 3345
 * Section: 001
 * Semester: Fall 2017
 * Project 6: Shortest path algorithm for planes based on time and cost
 */

/**
 *
 * @author Tiffany Do
 */
package project6;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Graph
{
    //HashMap to keep track of what vertex is associated with what name
    private HashMap<String, Vertex> nameVertex;
    public Graph()
    {
        nameVertex = new HashMap<>();
    }
    
    /** 
    * adds an edge to the graph given a source vertex, destination vertex, edge cost, and cost of other element (either time/cost depending on what the user queries)
    * 
    * @param source Source vertex
    * @param dest Destination vertex
    * @param edgeCost Cost of the edge
    * @param o Cost of the other element of the edge
    * 
    */
    public void addEdge(String source, String dest, int edgeCost, int o)
    {
        if(nameVertex.get(source) == null) //this is a new vertex
            nameVertex.put(source, new Vertex(source));
        if(nameVertex.get(dest) == null) //this is a new vertex
            nameVertex.put(dest, new Vertex(dest));
        Vertex v1 = nameVertex.get(source);
        Vertex v2 = nameVertex.get(dest);
        v1.adj.add(new Edge(v2, edgeCost, o));
    }
    
    /** 
    * Runs dijkstra's algorithm given a source vertex on the graph
    * 
    * @param sourceVertexName The vertex to be used as the source
    * 
    */
    public void dijkstra(String sourceVertexName)
    {
        //resets all vertices to infinite distance
        for(String key : nameVertex.keySet())
        {
            nameVertex.get(key).reset();
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Vertex source = nameVertex.get(sourceVertexName);
        if(source == null)
            return;
        pq.add(new Edge(source, 0, 0));
        source.dist = 0;
        //all other vertices are already initialized to infinity distance within vertex class
        while(!pq.isEmpty())
        {
            Edge remove = pq.remove();
            Vertex u = remove.vertex2;
            if(!u.known) //has not been processed
            {
                u.known = true;
                for(int i = 0; i < u.adj.size(); i++)
                {
                    //relaxation procedure on u
                    Vertex v = u.adj.get(i).vertex2;
                    int costvw = u.adj.get(i).cost;
                    int othervw = u.adj.get(i).other;
                    if(u.dist + costvw < v.dist)
                    {
                        v.dist = u.dist + costvw;
                        v.other = u.other + othervw;
                        v.path = u;
                        pq.add(new Edge(v, v.dist, v.other));
                    }
                }
            }
        }
    }
    
    /** 
    * Prints the path to a vertex after shortest path has been run
    * 
    * 
    * @param s name of the Vertex
    * @param out writes to stream
    */
    public void printPath(String s, PrintWriter out)
    {
        Vertex v = nameVertex.get(s);
        if(v == null)
            return;
        if(v.path != null)
        {
            printPath(v.path.name, out);
            out.print(" -> ");
        }
        out.print(v.name);
    }
    
    /** 
    * returns the cost of the queried element (time/cost)
    * 
    * @param s name of the Vertex
    * @return cost of the shortest path
    * 
    */
    public int returnCost(String s)
    {
        Vertex v = nameVertex.get(s);
        if(v == null)
            return Integer.MAX_VALUE;
        return v.dist;
    }
    
    /** 
    * returns the cost of the other element (time/cost)
    * 
    * @param s name of the Vertex
    * @return cost of the shortest path
    * 
    */
    public int returnOther(String s)
    {
        Vertex v = nameVertex.get(s);
        if(v == null)
            return Integer.MAX_VALUE;
        return v.other;
    }
  
}
