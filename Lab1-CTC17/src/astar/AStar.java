package astar;

import java.util.PriorityQueue;



import java.util.List;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex>
{
public final String name;
public double toGoal;
public Edge[] adjacencies;
public double minDistance = Double.POSITIVE_INFINITY;
public Vertex previous;
public Vertex(String argName) { name = argName; }
public String toString() { return name; }
public int compareTo(Vertex other)
{
    return Double.compare(minDistance, other.minDistance);
}
}

class Edge
{
public final Vertex target;
public final double weight;
public double toGoal;
public Edge(Vertex argTarget, double argWeight)
{ target = argTarget; weight = argWeight; }
}

class AStar
{
public static void computePaths(Vertex source)
{
    source.minDistance = 0.;
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source);

while (!vertexQueue.isEmpty()) {
	Vertex u = vertexQueue.poll();
    // Visit each edge exiting u

    for (Edge e : u.adjacencies)
    {   if(e==null)
    		break;
        Vertex v = e.target;
        double weight = e.weight;
        double distanceThroughU = u.minDistance + weight;
        double d = distanceThroughU;
if (d < v.minDistance) {
    vertexQueue.remove(v);
    v.minDistance = distanceThroughU ;
    v.previous = u;
    vertexQueue.add(v);
}
    }
}
}

public static List<Vertex> getShortestPathTo(Vertex target)
{
    List<Vertex> path = new ArrayList<Vertex>();
    for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
        path.add(vertex);
    Collections.reverse(path);
    return path;
}

public static void main(String[] args) throws ParseException
{
 

    CSVReader c = new CSVReader();
    Vertex[] vt = c.vertices;
    computePaths(vt[202]);
    
        System.out.println("Distance to " + vt[599] + ": " + vt[599].minDistance);
        List<Vertex> path = getShortestPathTo(vt[599]);
        System.out.println("Path: " + path);
    
}
}