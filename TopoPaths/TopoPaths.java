// Logan Isom
// COP 3503, Spring 2023
// NID lo854102
// TopoPaths.Java

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.lang.model.util.ElementScanner6;

public  class   TopoPaths
{
    public static int countTopoPaths(String  filename) throws IOException
    {
        // Take input from file to get the number of vertices
        Scanner in = new Scanner(new File(filename));
        int numPaths = 0;
        int numVert = in.nextInt();
        Graph graph = new Graph(numVert);
        int temp;

        // Check base cases off of vertice count
        if(numVert == 0)
            return 0;
        if(numVert == 1)
            return 1;
        
        // Add all of the vertices and what they point to into an adjacency list
        for(int i = 0; i < numVert; i++)
        {
            temp = in.nextInt();
            graph.vertices.add(i, new LinkedList<Integer>());
            for(int j = 0; j < temp; j++)
            {
                graph.vertices.get(i).add(in.nextInt());
            }    
        }

        numPaths += graph.checkTopoPaths(numVert);

        return  numPaths;
    }

    public static   double  difficultyRating()
    {
        return  4.0;
    }

    public static   double  hoursSpent()
    {
        return  20.0;
    }
}

class   Graph
{
    // Store the graph using an ArrayList of Linked Lists(Adjacency List)
    int numVertices;
    ArrayList<LinkedList<Integer>> vertices;

    Graph(int numVertices)
    {
        this.numVertices = numVertices;
        vertices = new ArrayList<LinkedList<Integer>>(numVertices);
    }

    // Loops through each vertice and then through each of the first vertices edges
    int checkTopoPaths(int numVert)
    {
        int numTopoPaths = 0;
        ArrayList<Integer> path = new ArrayList<Integer>();
        // Starting vertice
        for(int i = 0; i < numVert; i++)
        {
            path.add(i +  1);
            // Edges from starting vertice
            for(int j = 0; j < this.vertices.get(i).size(); j++)
            {
                path.add(this.vertices.get(i).get(j));
                numTopoPaths += lookDeeper(this.vertices.get(i).get(j), path, this, 1);
                path.remove(1);
            }
            path.clear();   
        }
        return numTopoPaths;
    }

    private int lookDeeper(int index, ArrayList<Integer> path, Graph graph, int iterator)
    {
        int numPaths = 0;
        iterator++;
        
        // Recursively look a layer deeper in the graph as long as the next vertice
        // Is not already apart of the path taken
        for(int i = 0; i < graph.vertices.get(index - 1).size(); i++)
        {
            if(path.contains(graph.vertices.get(index - 1).get(i)) == false)
            {
                path.add(graph.vertices.get(index - 1).get(i));
                numPaths += lookDeeper(graph.vertices.get(index - 1).get(i), path, graph, iterator);
            }
            else
            {
                return numPaths;
            }
              
        }
        
        // Path found if we have made it through all vertices
        if(iterator >= graph.numVertices)
            return numPaths + 1;

        // Path not found if the current vertice is already in the path
        if(graph.vertices.get(index - 1).size() != 0)
        {
            if(path.contains(graph.vertices.get(index - 1).get(0)));
                return numPaths + 0;
        }
        return numPaths;
    }
}
