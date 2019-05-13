public class LongestCommonSubstring {
    
    public static void printChart(int[][] A, char[] S1, char[] S2) {
        
        System.out.print("   ");
        for (int j = 0; j < S2.length; j++){
            System.out.print(S2[j]+"  ");
        }System.out.println();
    
        for (int i = 0; i < S1.length; i++){
            System.out.print(S1[i]+"  ");
                for (int j = 0; j < S2.length; j++){
                    System.out.print(A[i][j]+"  ");}
            System.out.println();}
        System.out.println();}
        
    public static void printArray(int[] array) {
        
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i]+"  ");
        }System.out.println();}
    
    public static void plusOneOnRightDownBlockOf(int[][] A, int i, int j){
        int S1_length = A.length;
        int S2_length = A[0].length;
        for (int ii=i;ii<S1_length;ii++ ){
            for (int jj=j;jj<S2_length;jj++ ){
                A[ii][jj] = A[ii][jj] + 1;
            }}};
            
    public static void func(int i, int j, int[][] A, char[] S1, char[] S2){
        if (i < S1.length && j < S2.length){
            if (S1[i] == S2[j]) { // match at the corner, go next
                A[i][j] = 1;
                func(i + 1, j + 1, A, S1, S2);
            }
            else{

                // Search downward.
                int iGoDown = i+1; // one box downward
                boolean foundInColumn = false;
                while (!foundInColumn && iGoDown < S1.length) {
                    if (S1[iGoDown] == S2[j]){
                        foundInColumn = true;
                        // save this to the left child
                        A[iGoDown][j] = 1;
                    }
                    iGoDown++;
                }
                if (foundInColumn) {
                    func(iGoDown - 1,j,A,S1,S2);  //  we have to use `iGoDown - 1` because we did `iGoDown++`
                }
            
                // Search rightward.
                int jGoRight = j + 1; // one box rightward
                boolean foundInRow = false;
                while (!foundInRow && jGoRight < S2.length) {
                    if (S1[i] == S2[jGoRight]){
                        foundInRow = true;
                        // save the matching one in the row to the right child
                        A[i][jGoRight] = 1;
                    }
                    jGoRight++;
                }
                if (foundInRow) {
                    func(i, jGoRight - 1, A, S1, S2); //  we have to use `iGoDown - 1` because we did `iGoDown++`
                }
                if (!foundInRow && !foundInRow) {
                    func(i + 1, j + 1, A, S1, S2);
                }
                
            }
        }
        else{
            // do nothing
            System.out.println("i of j is out of index");
        }
    }
    
    
            
    public static void LCM(char[] S1, char[] S2){
        char[] LCM;
        int[][] A = new int[S1.length][S2.length];
        int[] tree = new int[S2.length]; // choose the shorter as S2
        //   i,j        
        func(0,0,A,S1,S2);
       

        printChart(A, S1, S2);
        printArray(tree);
        // return something, maybe a tree
    }
        
    public static void main(String[] args) {
        char[] S1 = {'A','$','C','M','A','*','M','N'}; 
        //char[] S1 = {'A','B'}; 
        char[] S2 = {'A','X','M','C','4','A','N','B'};
        //char[] S2 = {'1','2','3','4','5'}; 
        LCM(S1, S2); // choose the longer as S1
        
        System.out.println("The end.");
    }
}
