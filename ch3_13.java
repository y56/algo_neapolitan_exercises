public class Main {
/*  static - 
        If you use static keyword in a method then it becomes static method.
        Static methods can be called without creating an instance of a class.

        For example, the sqrt() method of standard Math class is static.
        Hence, we can directly call Math.sqrt() without creating an instance of Math class.
*/
    public static void showMatrix(int M[][], int n) {
        for (int i = 0; i <= n-1; i++) {
            for (int j = 0; j <= n-1; j++) {
            System.out.printf("%5d, ", M[i][j]);
            }
        System.out.println();
        }
    }
    public static void order(int[][] myP, int i, int j) {
        if (i == j) {
            System.out.printf("A%d", i+1); // +1 is to fit the indexing of the exercise
        }
        else{
            int k = myP[i][j];
            System.out.printf("(");
            order(myP, i,k);
            order(myP, k+1,j);
            System.out.printf(")");

        }
    }
    public static int minMult(int n, int[] d) {

        int[][] M = new int[n][n];  // Set the M matrix.
        int[][] myP = new int[n][n];  // Set the myP matrix.
        int[][] P = new int[n][n];  // Set the P matrix for answering the exercise.
        for (int i = 0; i <= n-1; i++) {
            for (int j = 0; j <= n-1; j++) {
            M[i][j] = 0;
            myP[i][j] = 0;
            P[i][j] = 0;
            }
        }
        System.out.println("M[][] =");
        showMatrix(M, n);

        for (int diagonalIndex = 1; diagonalIndex <= n-1; diagonalIndex++) {  
            // n matrices need n-1 diagonals to solve the instance
            // We index the longest/middle diagonal as 0^th
            // The first diagonal we want to deal is 1^th
            // The minimum-multiplication number is the (n-1)^th diagonal, which only has one number in it.
            // There are (n-diagonalIndex) items in diagonalIndex^th diagonal 
            System.out.println("diagonalIndex = " + diagonalIndex); 
            for (int i = 0; i <= n-diagonalIndex-1; i++){  // `i` go though all items in a single diagonal
            
                int j = i + diagonalIndex; // A matrix element M[i][j] is at the (i-j)^th diagonal.

                int currentMin = Integer.MAX_VALUE;  // set a very large number
                int currentk = -1;
                int temp;

                for (int k = i; k <= j-1; k++){  // Executing i-(j-1)+1=i-j=diagonalIndex times
                    System.out.print("---  ");
                    System.out.print(i);  System.out.print(",  "); 
                    System.out.print(k+1);  System.out.print(",  ");
                    System.out.print(j+1);
                    System.out.print("  ---  ");
                    
                    temp = M[i][k] + M[k+1][j] + d[i]*d[k+1]*d[j+1];
                    
                    if (temp < currentMin){
                        System.out.printf("smaller!      %d < %d\n", temp, currentMin);
                        currentMin = temp;
                        currentk = k;
                    }
                    else{
                        System.out.printf("NOT smaller!      %d > %d\n", temp, currentMin);
                    }
                }
                M[i][j] = currentMin;
                myP[i][j] = currentk;
                P[i][j] = currentk + 1;
                System.out.print("Choose k = "); System.out.println(currentk);
                System.out.println("M[][] =");
                showMatrix(M, n);
            }
        }
        System.out.println("P[][] by the exercise's indexing =");
        showMatrix(P, n);

        System.out.println("multiplication order by the exercise's indexing =");
        order(myP, 0, n-1);
        System.out.printf("\n");

        return M[0][n-1];
    }
    public static void main(String[] args) {
        int n = 5;  // 5 matrices
        int[] d = new int[n+1];  // 6 subfix
        d[0] = 10;
        d[1] = 4;
        d[2] = 5;
        d[3] = 20;
        d[4] = 2;
        d[5] = 50;

        int minMultNum = minMult(n, d);

        System.out.println("FINISH!!!");
        System.out.println("(*￣▽￣)/‧☆*\"\u0060\'*-.,_,.-*\'\u0060\"*-.,_☆");
    }
}
