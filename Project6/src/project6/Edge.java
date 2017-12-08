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

//class to store edges
class Edge implements Comparable<Edge>
{
    //destination vertex of the edge
    public Vertex vertex2;
    public int cost;
    public int other;
    public Edge(Vertex v, int c, int o)
    {
        vertex2 = v;
        cost = c;
        other = o;
    }

    /** 
    * Comparable
    * 
    * @param Edge another edge to compare the edge to based on Edge cost, used in the PQ
    */
    @Override
    public int compareTo(Edge o) 
    {
        if (cost < o.cost)
            return -1;
        else if(cost > o.cost)
            return 1;
        else //they are equal
            return 0;
    }
}
