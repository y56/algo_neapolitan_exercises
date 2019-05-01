public class Main {
    /*
    static - 
        If you use static keyword in a method then it becomes static method.
        Static methods can be called without creating an instance of a class.

        For example, the sqrt() method of standard Math class is static.
        Hence, we can directly call Math.sqrt() without creating an instance of Math class.
    */
    public static int maxAndInex(int a) {
        System.out.println("call");
        return a;
    }

    public static int[][] minmult(int n, int[] d) {
        maxAndInex(2); // test how to call a function
        int[][] M = new int[n][n];

        // Set the matrix.
        for (int temp = 0; temp <= n-1; temp++) {
            for (int temptemp = 0; temptemp <= n-1; temptemp++) {
            M[temp][temptemp] = 0;
            //M[i][i] = 0;
            }
        }
        for (int diagonalIndex = 1; diagonalIndex <= n-1; diagonalIndex++) {  // n matrices need n-1 diagonals
            // We index the longest/middle diagonal as 0^th
            // The first diagonal we want to deal is 1^th
            // The minimum multiplication number is the (n-1)^th diagonal, which only has one number in it.
            // There are (n-diagonalIndex) items in diagonalIndex^th diagonal 
            System.out.println(diagonalIndex);
            System.out.println("diagonalIndex");
            for (int i = 0; i <= n-diagonalIndex-1; i++){  // `i` go though all items in one diagonal
            
                int j = i + diagonalIndex; // A matrix element M[i][j] is at the (i-j)t^h diagonal.
                int[] candidateArray = new int[2*n-1];  //
                int[] kArray = new int[n-1];  // n-1 is the max of the number of candidates
                
                System.out.println("before k loop");
                for (int k = i; k <= j-1; k++){
                    System.out.println("head");
                    System.out.println(i);
                    System.out.println(k+1);
                    System.out.println(j+1);  
                    System.out.println("tail");
                    candidateArray[k] = M[i][k] + M[k+1][j] + d[i]*d[k+1]*d[j+1];
                    kArray[k-i] = k;  // We will find the winner by 
                }
                /*
                int flag = 1;
                while (flag == 1) {
                    for (int kArrayIndex = 0; 
                            kArrayIndex <= Math.floor((diagonalIndex-1)/2); 
                            kArrayIndex++){
                        // floor((diagonal-1)/2) equal to floor((diagonal+1)/2)-1 where (diagonal+1) equal to length of kArrayIndex 
                        System.out.println(kArrayIndex);
                        if (candidateArray[kArray[kArrayIndex*2]] < candidateArray[kArray[kArrayIndex*2+1]]) {
                            kArray[kArrayIndex] = kArray[kArrayIndex*2];
                        }
                        else{
                            kArray[kArrayIndex] = kArray[kArrayIndex*2+1];
                        }
                    }
                flag = 0;
                }
                */
            }
        }
        
        return M;
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

        System.out.println("yoyo <3 PS");
        int[][] M = minmult(n, d);

        System.out.println("final");
    }
}
