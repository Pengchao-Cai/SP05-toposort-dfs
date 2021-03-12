/** Starter code for SP5
 *  @author rbk
 */

// change to your netid
package pxc190029;

import pxc190029.Graph.*;
import pxc190029.Graph.Edge;
import pxc190029.Graph.GraphAlgorithm;
import pxc190029.Graph.Factory;
import pxc190029.Graph.Timer;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
    public static class DFSVertex implements Factory {
	int cno;
	String status;
	int name;
	public DFSVertex(Vertex u) {
	    if(u != null) {
            this.name = u.getName();
            this.status = "unvisited";
        }
	}
	public DFSVertex make(Vertex u) { return new DFSVertex(u); }
    }

    public DFS(Graph g) {
	super(g, new DFSVertex(null));
    }

    public static DFS depthFirstSearch(Graph g) {
	return null;
    }

    // Member function to find topological order
    public List<Vertex> topologicalOrder1() {
        List<Vertex> res = new LinkedList<>();
        for (Graph.AdjList ele : this.g.adjList) {

            if (!isDAG(ele, res)) return null;

        }
	    return res;

    }

    public boolean isDAG(AdjList ele, List<Vertex> res) {
        Vertex cur = ele.vertex;
        DFSVertex curDFSVertex = this.store.get(cur);
        String status = curDFSVertex.status;
        if(status.equals("visited")) return true;
        if(status.equals("active")) return false;
        curDFSVertex.status = "active";
            for (Edge adjEdge : ele.outEdges) {
                AdjList nextEle = this.g.adj(adjEdge.toVertex());
                if (!isDAG(nextEle, res)) return false;
            }
        res.add(0, cur);
        curDFSVertex.status = "visited";
        return true;

    }

    // Find the number of connected components of the graph g by running dfs.
    // Enter the component number of each vertex u in u.cno.
    // Note that the graph g is available as a class field via GraphAlgorithm.
    public int connectedComponents() {
	return 0;
    }

    // After running the connected components algorithm, the component no of each vertex can be queried.
    public int cno(Vertex u) {
	return get(u).cno;
    }
    
    // Find topological oder of a DAG using DFS. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder1(Graph g) {
	DFS d = new DFS(g);
	return d.topologicalOrder1();
    }

    // Find topological oder of a DAG using the second algorithm. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder2(Graph g) {
	return null;
    }

    public static void main(String[] args) throws Exception {
	String string = "2 1    1 2 2    1 3 1    2 4 5   3 4 4   4 5 1    5 6 0   2 7 5 ";
	Scanner in;
	// If there is a command line argument, use it as file from which
	// input is read, otherwise use input from string.
	in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
	
	// Read graph from input
    Graph g = Graph.readDirectedGraph(in);
	g.printGraph(false);

//	 print topo result
        System.out.println("Topo result: ");
    List<Vertex> topoRes = DFS.topologicalOrder1(g);
        System.out.println(topoRes);

	
    }
}