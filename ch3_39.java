public class LongestCommonSubstring {
    
    public static void printChart(int[][] A, char[] S1, char[] S2) {
        
        System.out.print("     ");
        for (int j = 0; j < S2.length; j++){
            System.out.print(j + "    ");
        }
        System.out.println();
            
        System.out.print("    ");
        for (int j = 0; j < S2.length; j++){
            System.out.print("\'"+S2[j]+"\'"+"  ");
        }
        System.out.println();
    
        for (int i = 0; i < S1.length; i++) {
            System.out.printf("%d ", i);
            System.out.print(S1[i]);
                for (int j = 0; j < S2.length; j++){
                    System.out.printf("%3d  ", A[i][j]);
                }
            System.out.println();
        }
        System.out.println();
    }
        
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
                System.out.printf("(%d , %d )  ", i, j);
                System.out.println("A hit at the corner, go to next corner");
                A[i][j] += 1;
                func(i + 1, j + 1, A, S1, S2);
            }
            else{
                System.out.printf("(%d , %d )  ", i, j);
                System.out.println("No hits at the corner. ");
                A[i][j] += -7;

                // Search downward.
                int iGoDown = i+1; // one box downward
                boolean foundInColumn = false;
                while (!foundInColumn && iGoDown < S1.length) {
                    if (S1[iGoDown] == S2[j]){
                        System.out.printf("(%d , %d )  ", iGoDown, j);
                        System.out.println("A hit in the column.");
                        foundInColumn = true;
                        // save this to the left child
                        A[iGoDown][j] += 44;
                    }
                    else{
                        A[iGoDown][j] += -1;
                    }
                    iGoDown++;
                }
                if (foundInColumn) {
                    func(iGoDown, j + 1,A,S1,S2);  //  we have to use `iGoDown - 1` because we did `iGoDown++`
                }
                else{
                    System.out.printf("(%d , %d~)  ", iGoDown - 1, j);
                    System.out.println("No hit in column");
                }
            
                // Search rightward.
                int jGoRight = j + 1; // one box rightward
                boolean foundInRow = false;
                while (!foundInRow && jGoRight < S2.length) {
                    if (S1[i] == S2[jGoRight]){
                        System.out.printf("(%d , %d )  ", i, jGoRight);
                        System.out.println("A hit in the row.");
                        foundInRow = true;
                        // save the matching one in the row to the right child
                        A[i][jGoRight] += 44;
                    }
                    else{
                         A[i][jGoRight] -= 1;
                    }
                    jGoRight++;
                }
                if (foundInRow) {
                    func(i + 1, jGoRight, A, S1, S2); 
                }
                else{
                    System.out.printf("(%d , %d~)  ", i, jGoRight - 1); //  we have to use `iGoDown - 1` because we did `iGoDown++`
                    System.out.println("No hit in row");
                }
                // No hits in the column and the row, go to the next corner
                if (!foundInRow && !foundInRow) {
                    System.out.printf("(%d~, %d~)  ", i, j);
                    System.out.println("No hits in column or row, go to next corner.");
                    func(i + 1, j + 1, A, S1, S2);
                }
                
            }
        }
        else{
            // do nothing
            System.out.println("i or j is out of index");
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
