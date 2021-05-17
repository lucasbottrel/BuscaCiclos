
class Busca_Ciclos_Permutacao {
    

    static int ciclos = 1;

    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static void combinationUtil(String arr[], String data[], int start, int end, int index, int r)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            
            System.out.print("Ciclo " + ciclos + ": ");
            ciclos++;
            for (int j=0; j<r; j++){
                
                System.out.print(data[j]+" ");
            }
            System.out.println("");
            return;
        }
 
        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r);
        }
    }
 
    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(String arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        String data[]=new String[r];
 
        // Print all combination using temprary array 'data[]'
        combinationUtil(arr, data, 0, n-1, 0, r);
    }
 
    /*Driver function to check for above function*/
    public static void main (String[] args) {
        
        long inicio = System.currentTimeMillis();

        int vertices[] = {1, 2, 3, 4, 5, 6};
        int r = 3;
        int n = vertices.length;

        String[] graph = {
            "{1, 2}", "{1, 4}", "{1, 5}", 
			"{2, 3}", "{2, 4}", "{2, 5}", 
			"{4, 3}", "{4, 6}", "{3, 6}", 
			"{3, 5}", "{5, 6}"};
        
        for (; r <= vertices.length; r++){
            printCombination(graph, n, r); 
        }
		
		int numVertices = 6;
        
		System.out.println("\nTEMPO GASTO: " + (System.currentTimeMillis() - inicio) + "ms");
    }
}