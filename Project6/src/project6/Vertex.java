/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

import java.util.LinkedList;

/**
 *
 * @author Chawp
 */
public class Vertex 
{
    public String name;
    public LinkedList<Edge> adj;
    public boolean known;
    public int dist;
    public Vertex path;
    
    public Vertex(String name)
    {
        this.name = name;
        adj = new LinkedList<>();
        dist = Integer.MAX_VALUE; //set distance to infinity
        path = null;
        known = false;
    }
}
