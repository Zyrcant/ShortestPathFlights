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

import java.util.LinkedList;

//Vertex class
public class Vertex 
{
    public String name;
    public LinkedList<Edge> adj;
    public boolean known;
    public int dist;
    public int other;
    public Vertex path;

    public Vertex(String name)
    {
        this.name = name;
        adj = new LinkedList<>();
        reset();
    }
    
    /** 
    * Resets the vertex distance to infinity and other elements as well
    */
    public final void reset()
    {
        dist = Integer.MAX_VALUE; //set distance to infinity
        other = 0;
        path = null;
        known = false;
    }
}
