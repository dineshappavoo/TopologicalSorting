/**
 * 
 */
package topologicalsorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * @author Dany
 *
 */
public class TopologicalSorting {

	public static Queue<Integer> VertexWithoutIncoming=new LinkedList<Integer>();
	public static ArrayList<Integer> topologicalOrder=new ArrayList<Integer>();
	public int[] inEdgesCount=null;
	public static int noOfVertices,noOfEdges;
	public static Graph graph=null;


	public static void main(String[] args) {

		TopologicalSorting topologicalSort=new TopologicalSorting();
		ArrayList<Integer> topoSort=topologicalSort.getTopologicalOrder();

		for(int n : topoSort)
		{
			System.out.print(" "+n);
		}

	}

	public ArrayList<Integer> getTopologicalOrder()
	{
		constructGraph();
		doTopologicalSorting();
		return topologicalOrder;
	}

	public void constructGraph() 
	{
		//File file=new File(sFile);
		Scanner scanner=new Scanner(System.in);
		int u, v, w;
		while(scanner.hasNext())
		{
			noOfVertices=scanner.nextInt();
			noOfEdges=scanner.nextInt();

			graph=new Graph(noOfVertices);

			for(int i=0;i<noOfEdges;i++)
			{
				u=scanner.nextInt();
				v=scanner.nextInt();
				w=scanner.nextInt();
				graph.addEdge(u, v, w);
			}
			break;
		}
	}


	public void findVerticesWithOutIncomingEdge()
	{
		inEdgesCount=graph.getInEdgesCount();
		for(int i=1;i<=noOfVertices;i++)
		{
			if(inEdgesCount[i]==0)
				VertexWithoutIncoming.add(i);
		}

	}

	/**
	 * 
	 * 
	 * 
	 * L <- Empty list that will contain the sorted elements
	   S <- Set of all nodes with no incoming edges
		while S is non-empty do
    		remove a node n from S
    		add n to tail of L
    		for each node m with an edge e from n to m do
        		remove edge e from the graph
        		if m has no other incoming edges then
            	insert m into S
	   if graph has edges then
    		return error (graph has at least one cycle)
	   else 
    		return L (a topologically sorted order)
	 * 
	 */
	public void doTopologicalSorting()
	{
		topologicalOrder=new ArrayList<Integer>();
		findVerticesWithOutIncomingEdge();

		while(!VertexWithoutIncoming.isEmpty())
		{
			int n=VertexWithoutIncoming.poll();
			topologicalOrder.add(n);
			ArrayList<Edge> outEdges=graph.getOutEdges(n);
			for(Edge e : outEdges)
			{
				inEdgesCount[e.v]--;
				if(inEdgesCount[e.v]==0)
					VertexWithoutIncoming.add(e.v);
			}

		}
		
	}
}
