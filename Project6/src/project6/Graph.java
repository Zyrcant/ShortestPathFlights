/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Chawp
 */
public class Graph
{
    private HashMap<String, Vertex> nameVertex;
    public Graph()
    {
        nameVertex = new HashMap<>();
    }
    public void addEdge(String source, String dest, int edgeCost, int o)
    {
        if(nameVertex.get(source) == null) //this is a new vertex
            nameVertex.put(source, new Vertex(source, o));
        if(nameVertex.get(dest) == null) //this is a new vertex
            nameVertex.put(dest, new Vertex(dest, o));
        Vertex v1 = nameVertex.get(source);
        Vertex v2 = nameVertex.get(dest);
        v1.adj.add(new Edge(v2, edgeCost));
    }
    public void dijkstra(String sourceVertexName)
    {
        for(String key : nameVertex.keySet())
        {
            nameVertex.get(key).reset();
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Vertex source = nameVertex.get(sourceVertexName);
        if(source == null)
            return;
        pq.add(new Edge(source, 0));
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
                    if(u.dist + costvw < v.dist)
                    {
                        v.dist = u.dist + costvw;
                        v.path = u;
                        pq.add(new Edge(v, v.dist));
                    }
                }
            }
        }
    }
    public void printPath(String s)
    {
        Vertex v = nameVertex.get(s);
        if(v == null)
            return;
        if(v.path != null)
        {
            printPath(v.path.name);
            System.out.print(" -> ");
        }
        System.out.print(v.name);
    }

    public int returnCost(String s)
    {
        Vertex v = nameVertex.get(s);
        if(v == null)
            return Integer.MAX_VALUE;
        return v.dist;
    }
    
    public int returnOther(String s)
    {
        Vertex v = nameVertex.get(s);
        if(v == null)
            return 0;
        return returnOther(v, 0);
    }
    public int returnOther(Vertex v, int i)
    {
       int x = i;
       if(v.path != null)
       {
           x += v.path.other;
           x = returnOther(v.path, x);
       }
       return x;
    }
}
