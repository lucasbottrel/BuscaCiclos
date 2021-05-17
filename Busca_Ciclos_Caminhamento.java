import java.util.ArrayList;
import java.util.List;

public class Busca_Ciclos_Caminhamento {

    static int[][] graph = { {1, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {4, 3}, {4, 6}, {3, 6}, {3, 5}, {5, 6} };
    static List<int[]> cycles = new ArrayList<int[]>();

    public static void main(String[] args) {

        long inicio = System.currentTimeMillis();

        for (int i = 0; i < graph.length; i++){
            for (int j = 1; j < graph[i].length; j++){
                findNewCycles(new int[] {graph[i][j]});
            }
        }
        
        int cont = 1;
        for (int[] cy : cycles) {
            
            String s = "" + cy[0];

            for (int i = 1; i < cy.length; i++) s += " " + cy[i];

            System.out.println("Ciclo " + cont + ": " + s);
            cont++;
        }

        long fim = System.currentTimeMillis();

        System.out.println("\nTEMPO GASTO: " + (fim-inicio) + "ms");
    }

    public static void findNewCycles(int[] path){
        
        int n = path[0];
        int x;
        int[] sub = new int[path.length + 1];

        for (int i = 0; i < graph.length; i++){
            for (int y = 0; y <= 1; y++){
                if (graph[i][y] == n){
                    
                    x = graph[i][(y + 1) % 2];
                    if (!visited(x, path)) {
                        sub[0] = x;
                        System.arraycopy(path, 0, sub, 1, path.length);
                        findNewCycles(sub);

                    } else if ((path.length > 2) && (x == path[path.length - 1])) {
                        int[] p = normalize(path);
                        int[] inv = invert(p);

                        if (isNew(p) && isNew(inv)) {
                            cycles.add(p);
                        }
                    }
                }
            }
        }
    }

    public static Boolean equals(int[] a, int[] b) {
        Boolean ret = (a[0] == b[0]) && (a.length == b.length);

        for (int i = 1; ret && (i < a.length); i++) {
            if (a[i] != b[i]) ret = false;
        }

        return ret;
    }

    public static int[] invert(int[] path) {
        
        int[] p = new int[path.length];

        for (int i = 0; i < path.length; i++) {
            p[i] = path[path.length - 1 - i];
        }

        return normalize(p);
    }

    public static int[] normalize(int[] path) {
        
        int[] p = new int[path.length];
        int x = smallest(path);
        int n;

        System.arraycopy(path, 0, p, 0, path.length);

        while (p[0] != x) {
            n = p[0];
            System.arraycopy(p, 1, p, 0, p.length - 1);
            p[p.length - 1] = n;
        }

        return p;
    }

    public static Boolean isNew(int[] path) {
        Boolean ret = true;

        for(int[] p : cycles){
            if (equals(p, path)) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static int smallest(int[] path){
        int min = path[0];

        for (int p : path) if (p < min) min = p;

        return min;
    }

    public static Boolean visited(int n, int[] path){
        Boolean ret = false;

        for (int p : path){
            if (p == n){
                ret = true;
                break;
            }
        }

        return ret;
    }
}