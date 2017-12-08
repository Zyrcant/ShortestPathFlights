/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Chawp
 */
public class Project6 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException 
    {
        File file = new File("data.txt");
        File file2 = new File("query.txt");

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
            gCost.addEdge(input[0], input[1], Integer.parseInt(input[2]));
            gTime.addEdge(input[0], input[1], Integer.parseInt(input[3]));
        }

        Scanner sc = new Scanner(file2);
        int loop = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < loop; i++)
        {
            String inputLine = sc.nextLine();
            String[] input = inputLine.split("\\|");
            gTime.dijkstra(input[0]);
            gCost.dijkstra(input[0]);
            if(input[2].equals("T"))
            {
                if(gTime.returnCost(input[1]) == Integer.MAX_VALUE)
                    System.out.println("NO FLIGHT AVAILABLE FOR THE REQUEST");
                else
                {
                    System.out.println(input[0] + ", " + input[1] + " (Time)");
                    gTime.printPath(input[1]);
                    System.out.println(". Time: " + gTime.returnCost(input[1]) + " Cost: " +gCost.returnCost(input[1]) + ".00");
                }
            }
            else if (input[2].equals("C"))
            {
                gCost.dijkstra(input[0]);
            }
            
        }

    }
    
}
