/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

/**
 *
 * @author Chawp
 */
class Edge implements Comparable<Edge>
{
    public Vertex vertex2;
    public int cost;
    public Edge(Vertex v, int c)
    {
        vertex2 = v;
        cost = c;
    }

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
