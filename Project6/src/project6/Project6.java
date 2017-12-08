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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Project6 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException 
    {
        //command line arguments must be provided in the order of data, query, and output
        String data = args[0];
        String query = args[1];
        String output = args[2];
        File file = new File(data);
        File file2 = new File(query);
        PrintWriter writer = new PrintWriter(output);

        //adds edges and creates graph
        Scanner sc2 = new Scanner(file);
        int num = sc2.nextInt();
        sc2.nextLine();
        Graph gTime = new Graph();
        Graph gCost = new Graph();
        for(int i = 0; i < num; i++)
        {
            String inputLine = sc2.nextLine();
            String[] input = inputLine.split("\\|");
            gCost.addEdge(input[0], input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
            gTime.addEdge(input[0], input[1], Integer.parseInt(input[3]), Integer.parseInt(input[2]));
        }
        
        //scans query and calculates shortest paths
        Scanner sc = new Scanner(file2);
        int loop = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < loop; i++)
        {
            String inputLine = sc.nextLine();
            String[] input = inputLine.split("\\|");
            //runs dijkstras algorithm based on source
            gTime.dijkstra(input[0]);
            gCost.dijkstra(input[0]);
            if(gTime.returnCost(input[1]) == Integer.MAX_VALUE)
                writer.println("NO FLIGHT AVAILABLE FOR THE REQUEST");
            else
            {
               if(input[2].equals("T"))
               {
                    writer.println(input[0] + ", " + input[1] + " (Time)");
                    gTime.printPath(input[1], writer);
                    writer.println(". Time: " + gTime.returnCost(input[1]) + " Cost: " + gTime.returnOther(input[1]) + ".00");
                    writer.println();
               }
               else
               {
                   writer.println(input[0] + ", " + input[1] + " (Cost)");
                   gCost.printPath(input[1], writer);
                   writer.println(". Time: " + gCost.returnOther(input[1]) + " Cost: " + gCost.returnCost(input[1]) + ".00");
                   writer.println();
               }
            }
        }
        writer.close();
    }
    
}
