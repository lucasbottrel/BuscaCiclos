// Java program to find single cycle components
// in a graph.
import java.util.*;

class Busca_Ciclos_Permutacao {

	static int N = 100000;

	// grau de todos vértices
	static int degree[] = new int[N];

	// vértices visitados
	static boolean found[] = new boolean[N];

	// vértices pertencentes a um componente conexo
	static Vector<Integer> curr_graph = new Vector<Integer>();

	// lista de adjacência
	static Vector<Vector<Integer>> adj_list = new Vector<Vector<Integer>>();

	// busca em profundidade para identificar vértices em um componente conexo
	static void DFS(int v)
	{
		found[v] = true;
		curr_graph.add(v);
		for (int it = 0 ;it < adj_list.get(v).size(); it++)
			if (!found[adj_list.get(v).get(it)])
				DFS(adj_list.get(v).get(it));
	}

	// adiciona aresta no grafo
	static void addEdge( int src,int dest)
	{
		src--; dest--;
		adj_list.get(src).add(dest);
		adj_list.get(dest).add(src);
		degree[src]++;
		degree[dest]++;
	}

	static int countSingleCycles(int n, int m)
	{
		// conta ciclos 
		int count = 0;
		for (int i = 0; i < n; ++i)
		{

			if (!found[i])
			{
				curr_graph.clear();
				
				DFS(i);

				// visita vértices do componente atual
				int flag = 1;
				for (int v = 0 ; v < curr_graph.size(); v++)
				{
					if (degree[curr_graph.get(v)] == 2)
						continue;
					else
					{
						flag = 0;
						break;
					}
				}
				if (flag == 1)
				{
					count++;
				}
			}
		}
		return(count);
	}

	public static void main(String args[])
	{
		
		for(int i = 0; i < N + 1; i++)
		adj_list.add(new Vector<Integer>());
		
		// n->numero de vertices
		// m->numero de arestas
		int n = 15, m = 14;
		addEdge( 1, 10);
		addEdge( 1, 5);
		addEdge( 5, 10);
		addEdge( 2, 9);
		addEdge( 9, 15);
		addEdge( 2, 15);
		addEdge( 2, 12);
		addEdge( 12, 15);
		addEdge( 13, 8);
		addEdge( 6, 14);
		addEdge( 14, 3);
		addEdge( 3, 7);
		addEdge( 7, 11);
		addEdge( 11, 6);
		

		System.out.println("Numero de Ciclos" + countSingleCycles(n, m));
	}
}

// This code is contributed by Arnab Kundu
