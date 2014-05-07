package topologicalsorting;


import java.util.ArrayList;

/**
 * @author Dany
 *
 */
public class Graph {


	public static ArrayList<Edge>[] adjacencyList=null;
	public static int[] inEdgeCount=null;
	public int noOfVertices;


	public Graph(int noOfVertices)
	{
		adjacencyList=(ArrayList<Edge>[])new ArrayList[noOfVertices+1];
		//TO initialize with empty arrayList
		for(int i=1;i<=noOfVertices;i++)
		{
			adjacencyList[i]=new ArrayList<Edge>();
		}
		inEdgeCount=new int[noOfVertices+1];
		this.noOfVertices=noOfVertices;
	}

	/**
	 * 
	 * @param u
	 * @param v
	 * @param w
	 * To add edges to the adjacency list graph
	 */
	public void addEdge(int u, int v, int w)
	{
		if(adjacencyList[u]==null)
			adjacencyList[u]=new ArrayList<Edge>();
		adjacencyList[u].add(new Edge(u, v, w));
	}

	/**
	 * 
	 * @param u
	 * @param v
	 * To remove the edge from the graph
	 */
	public void removeEdge(int u, int v)
	{
		int indexToBeRemoved=-1;
		ArrayList<Edge> edgeList=adjacencyList[u];
		for(int i=0;i<adjacencyList[u].size();i++)
		{
			Edge e=edgeList.get(i);
			if(e.u==u&&e.v==v)
			{
				indexToBeRemoved=i;
			}
		}
		if(indexToBeRemoved!=-1)
			edgeList.remove(indexToBeRemoved);
	}

	/**
	 * 
	 * @param u
	 * @return
	 * To return the outgoing edges for the given source
	 */
	public ArrayList<Edge> getOutEdges(int u)
	{
		return adjacencyList[u];
	}

	/**
	 * 
	 * @param u
	 * @param v
	 * @return
	 * To get the weight given the u and v values
	 * 
	 */
	public int getWeight(int u, int v)
	{
		ArrayList<Edge> edgeList=adjacencyList[u];
		int weight=0;
		for(Edge e : edgeList)
		{
			if(e.v==v)
				weight=e.w;
		}
		return weight;
	}

	/**
	 * To return the array of adjacency list(Actually the graph)
	 * @return
	 */
	public ArrayList<Edge>[] getListOfAdjacencylist()
	{
		return adjacencyList;
	}

	/**
	 * To return the count of incoming edges for each vertices in an array
	 * @return
	 */
	public int[] getInEdgesCount()
	{
		for(int i=1;i<=noOfVertices;i++)
		{
			for(int j=1;j<adjacencyList.length;j++)
			{
				ArrayList<Edge> list=adjacencyList[j];
				for(Edge e : list)
				{
					if(e.v==i)
						inEdgeCount[i]++;
				}
			}

		}
		return inEdgeCount;
	}

	/**
	 * To Print the Graph
	 */
	public void printGraph()
	{
		ArrayList<Edge> edgeList;
		for(int i=1;i<noOfVertices;i++)
		{
			edgeList=adjacencyList[i];
			for(Edge e : edgeList)
				System.out.println("u : "+e.u+" v : "+e.v+" w : "+e.w);
		}
	}
	/**
	 * 
	 * @param args
	 * Main function to test the graph
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph graph=new Graph(3);
		graph.addEdge(1, 2, 5);
		graph.addEdge(1, 3, 12);
		graph.addEdge(3, 1, 12);
		graph.addEdge(2, 1, 5);
		graph.addEdge(2, 3, 7);
		graph.addEdge(3, 2, 7);
		graph.printGraph();

	}



}
