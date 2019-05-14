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
    
    public static class Node {
        
        public char myChar;
        public Node leftChild;
        public Node rightChild;
        
        public void append(Node childNode) {
            if  (null == leftChild) {
                this.leftChild = childNode;
            }
            else if (null == rightChild) {
                this.rightChild = childNode;
            }
            else { // left and right both already have child  
                System.out.println("Can't have more children");
            }
        }
        public Node() {
            this.myChar = '\u0000';
        }
        public Node(char newChar) {
            this.myChar = newChar;
        }
    }
            
    public static void fillChartPlantTree(int i, int j, int[][] A, char[] S1, char[] S2, Node node){
        if (i < S1.length && j < S2.length){
            if (S1[i] == S2[j]) { // match at the corner, go next
                System.out.printf("(%d , %d )  ", i, j);
                System.out.println("A hit at the corner, go to next corner");
                A[i][j] += 1;
                Node newNode = new Node(S1[i]);
                node.append(newNode);
                fillChartPlantTree(i + 1, j + 1, A, S1, S2, newNode);
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
                        A[iGoDown][j] += 44;
                        // save this as left child
                        Node newNode = new Node(S1[iGoDown]);
                        node.append(newNode);
                        fillChartPlantTree(iGoDown + 1, j + 1,A,S1,S2,newNode);
                    }
                    else{
                        A[iGoDown][j] += -1;
                        System.out.printf("(%d , %d~)  ", iGoDown - 1, j);
                        System.out.println("No hit in column");
                    }
                    iGoDown++;
                }
                // Search rightward.
                int jGoRight = j + 1; // one box rightward
                boolean foundInRow = false;
                while (!foundInRow && jGoRight < S2.length) {
                    if (S1[i] == S2[jGoRight]) {
                        System.out.printf("(%d , %d )  ", i, jGoRight);
                        System.out.println("A hit in the row.");
                        foundInRow = true;
                        A[i][jGoRight] += 44;  // mark in the chart
                        Node newNode = new Node(S1[i]); // save the matching one in the row as the right child 
                        node.append(newNode);
                        fillChartPlantTree(i + 1, jGoRight + 1, A, S1, S2, newNode);
                    }
                    else{
                        A[i][jGoRight] -= 1;
                        System.out.printf("(%d , %d~)  ", i, jGoRight); 
                        System.out.println("No hit in row");
                    }
                    jGoRight++;
                }
                // No hits in the column or row, go to the next corner
                if (!foundInRow && !foundInColumn) {
                    System.out.printf("(%d~, %d~)  ", i, j);
                    System.out.println("No hits in column or row, go to next corner.");
                    fillChartPlantTree(i + 1, j + 1, A, S1, S2, node);
                }
            }
        }
        else{
            // do nothing
            System.out.printf("(%d , %d )  ", i, j);
            System.out.println("i or j is out of index");
        }
    }
    
    public static void printAlongNodes(Node node) {
        if (null != node) {
            if ('\u0000' != node.myChar) {
                System.out.println(node.myChar);
                printAlongNodes(node.leftChild);
                printAlongNodes(node.rightChild);
            }
            else{
                System.out.println("null_char_as_head");
                printAlongNodes(node.leftChild);
                printAlongNodes(node.rightChild);
            }
        }
    }
    public static void LCS(char[] S1, char[] S2){
        
        int[][] A = new int[S1.length][S2.length];  // initialize a chart

        Node head = new Node(); // create the head of a tree
        
        fillChartPlantTree(0,0,A,S1,S2,head);
        
        System.out.println();
        printAlongNodes(head);
        
        System.out.println();
        printChart(A, S1, S2);
    }
        
    public static void main(String[] args) {
        //char[] S1 = {'A','$','C','M','A','*','M','N'}; 
        char[] S1 = {'A', 'X', 'M', 'G', 'J', 'K', 'C', '4', 'A', 'N', 'B'};
        char[] S2 = {'A', '$', 'C', '4', 'I', 'Q', 'M', 'A', '*', 'M', 'N'};
        
        //char[] S1 = {'A','B'}; 
        //char[] S2 = {'A','X','M','C','4','A','N','B'};
        //char[] S2 = {'1','2','3','4','5'}; 
        LCS(S1, S2); // choose the longer as S1
        
        System.out.println("The end.");
    }
}
