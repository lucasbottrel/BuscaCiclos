// Programa em Java para escrever os ciclos em um grafo não direcionado
import java.util.*;
 
class Busca_Ciclos_Caminhamento {
 
    static final int N = 100000;
 
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] graph = new Vector[N];
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] cycles = new Vector[N];
    static int cyclenumber;
 
    // Função que marca os vértices com cores diferentes para ciclos diferentes
    static void dfs_cycle(int u, int p, int[] color, int[] mark, int[] par) {
 
        // vértice visitado.
        if (color[u] == 2) {
            return;
        }
 
        // vértices visitado mas não completamente -> ciclo detectado.
        if (color[u] == 1) {
 
            cyclenumber++;
            int cur = p;
            mark[cur] = cyclenumber;
 
            // volta no vértice no ciclo atual
            while (cur != u)
            {
                cur = par[cur];
                mark[cur] = cyclenumber;
            }
            return;
        }
        par[u] = p;
 
        // parcialmente visitado
        color[u] = 1;

        for (int v : graph[u])
        {
 
            // se não foi visitado anteriormente
            if (v == par[u])
            {
                continue;
            }
            dfs_cycle(v, u, color, mark, par);
        }
 
        // completamente visitado.
        color[u] = 2;
    }
 
    // adiciona arestas no grafo
    static void addEdge(int u, int v)
    {
        graph[u].add(v);
        graph[v].add(u);
    }
 
    // função para printar os ciclos
    static void printCycles(int edges, int mark[])
    {
 
        // empurra as arestas que estão no ciclo da lista de adjacência
        for (int i = 1; i <= edges; i++)
        {
            if (mark[i] != 0)
                cycles[mark[i]].add(i);
        }
 
        // escreve todos os vértices de um mesmo ciclo
        for (int i = 1; i <= cyclenumber; i++)
        {
            // Print the i-th cycle
            System.out.printf("Ciclo Numero %d: ", i);
            for (int x : cycles[i])
                System.out.printf("%d ", x);
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
 
        for (int i = 0; i < N; i++)
        {
            graph[i] = new Vector<>();
            cycles[i] = new Vector<>();
        }
 
        // add edges
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(4, 6);
        addEdge(4, 7);
        addEdge(5, 6);
        addEdge(3, 5);
        addEdge(7, 8);
        addEdge(6, 10);
        addEdge(5, 9);
        addEdge(10, 11);
        addEdge(11, 12);
        addEdge(11, 13);
        addEdge(12, 13);
 
        // arrays da cor do grafo, armazena os parentes do vértice
        int[] color = new int[N];
        int[] par = new int[N];
 
        int[] mark = new int[N];
 
        // guarda numero de ciclos
        cyclenumber = 0;
        int edges = 13;
 
        // chama a busca em profundidade com os vértices marcados
        dfs_cycle(1, 0, color, mark, par);
 
        printCycles(edges, mark);
    }
}
 
// This code is contributed by
// sanjeev2552