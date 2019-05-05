public class Main {
    public static void printDoubleArray(double[][] M) {
        int N = M.length;
        int NN = M[0].length;
        for (int i = 0; i <= NN; i++){
                for (int j = 0; j <= N-2; j++){
                    System.out.print(M[i][j]+"     ");    
                }
            System.out.println();
            }
    }
    public static void printIntArray(int[][] M) {
        int N = M.length;
        int NN = M[0].length;
        for (int i = 0; i <= NN; i++){
                for (int j = 0; j <= N-2; j++){
                    System.out.print(M[i][j]+"     ");    
                }
            System.out.println();
            }
    }
    public static double sumPArrayOver(int i, int j, double[] pArray) {
        double sum = 0;
        System.out.print(i + "     ");
        System.out.println(j + "===== sum over");
        for (int k = i; k <= j; k++){
            sum = sum + pArray[k];
        }
        return sum;
    }
    public static class MinCostAndk {
        double minCost;
        int k;   
        MinCostAndk(int i, int j, double[][] A){ // constructor
            double currentMinCost =  Double.MAX_VALUE;
            int currentk = 0;
            for (int k = i; k <= j; k++){
                System.out.print("i,j,k    " + i + "    " + j + "    " + k);
                if (A[i][k-1] + A[k+1][j] < currentMinCost){
                    System.out.println("<  <  <  <");
                    currentMinCost = A[i][k-1] + A[k+1][j];
                    currentk = k;
                }
                else{
                    System.out.println(">  >  >  >");
                }
            }
            this.minCost = currentMinCost;
            this.k = currentk;
        }
    }
    public static class ArraysForOBST {
        double[][] A;
        int[][] R;
        public ArraysForOBST(double[] pArray){ // constructor
            int n = pArray.length-1;
            A = new double[n+2][n+1]; // the initail value will be 0.0
            R = new int[n+2][n+1];
            printDoubleArray(A); System.out.println(); printIntArray(R);
            for (int i = 0; i <= n+1; i++){
                for (int j = 0; j <= n; j++){ 
                    A[i][j] = Float.NaN;
                    R[i][j] = -1;
                }
            }
            printDoubleArray(A); System.out.println(); printIntArray(R);
            for (int i = 1; i <= n; i++){
                A[i][i-1] = 0;
                A[i][i] = pArray[i];
                R[i][i] = i;
                R[i][i-1] = 0;
            }
            printDoubleArray(A); System.out.println(); printIntArray(R);
            A[n+1][n] = 0.0;
            R[n+1][n] = 0;
            printDoubleArray(A); System.out.println(); printIntArray(R);
            for (int diagonalIndex = 1; diagonalIndex <= n-1; diagonalIndex++){
                for (int iInDiagonal = 1; iInDiagonal <= n-diagonalIndex; iInDiagonal++) {
                    int i = iInDiagonal; // the i for A[][] is the same as the iInDiagonal 
                    int j = iInDiagonal + diagonalIndex; 
                    // the 1st item in diagonalIndex^th diagonal is A[diagonalIndex][j]
                    MinCostAndk minCostAndk = new MinCostAndk(i, j, A);
                    A[i][j] = minCostAndk.minCost + sumPArrayOver(i, j, pArray);
                    R[i][j] = minCostAndk.k;
                }
            }
            printDoubleArray(A); System.out.println(); printIntArray(R);
            this.A = A; 
            this.R = R;
        }
         
    }
    public static class Node {
        int keyValue;
        Node left; 
        Node right;
        
        Node(int keyValue) {
            this.keyValue = keyValue;
            left = null;
            right = null;
        }
    }
    public static Node planter(int i, int j, int[][] R ) {
        int keyValue = R[i][j];
        if (keyValue == 0) {
            return null;
        }
        else{
            Node node = new Node(keyValue);
            node.left = planter(i, keyValue-1, R);
            node.right = planter(keyValue+1, j, R);
            return node;
        }
    }
    public static void printATree(Node node, String[] wordArray) {
        if (node == null){
            System.out.println("A Leaf has no further nodes!!!\n");
        }
        else {
            System.out.print(node.keyValue + "   ");
            System.out.println(wordArray[node.keyValue]);
            System.out.println();
            printATree(node.left, wordArray);
            printATree(node.right, wordArray);
        }
    }
    public static void main(String[] args) {
        // The keys are the indeces of the two arrays below.
        String[] wordArray = {"aaa","CASE", "ELSE", "END", "IF", "OF", "THEN"};
        double[] pArray = {-99.9999, 0.05,   0.15,   0.05,  0.35, 0.05, 0.35};
        //double[] pArray = {-99.9, 3./8., 3./8., 1./8., 1./8.};
        int n  = pArray.length-1;
        
        // find the arrangement of the tree
        ArraysForOBST arraysForOBST  = new ArraysForOBST(pArray);
        int[][] R = arraysForOBST.R;
        double[][] A = arraysForOBST.A;
        double optimalCost = A[1][n];
        System.out.println(optimalCost);
        
        // plant the tree
        Node root = planter(1, n, R);
        
        // show the tree
        printATree(root, wordArray);
        
        System.out.println("The end.");
    }
}
