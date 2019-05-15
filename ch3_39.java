public class LongestCommonSubstring {
    
    // draw a chart for dynamic programming
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
    // prepare a `Node` class to plant a tree
    public static class Node {
        
        public char myChar;  // a character contained in a node
        public Node leftChild;
        public Node rightChild;
        
        public void append(Node childNode) {
            if  (null == leftChild) {  // start to add a child from the left
                this.leftChild = childNode;
            }
            else if (null == rightChild) {  // if the left is used, use the right
                this.rightChild = childNode;
            }
            else { // left and right both already have child  
                System.out.println("Can't have more children");
            }
        }
        public Node() {  // a constructor without input 
            this.myChar = '\u0000';  // use null char as default
        }
        public Node(char newChar) { // a constructor with input
            this.myChar = newChar;
        }
    }
    // fill the DP chart, and plant a tree containing matching chars
    public static void fillChartPlantTree(int i, int j, int[][] A, char[] S1, char[] S2, Node node){
        if (i < S1.length && j < S2.length){  // if indecies are within bound
            if (S1[i] == S2[j]) { // match at the corner, go next
                System.out.printf("(%d , %d )  ", i, j);
                System.out.println("A hit at the corner, go to next corner");
                A[i][j] += 1; // fill the DP chart
                Node newNode = new Node(S1[i]);  // create a new node, using the matching char
                node.append(newNode);
                fillChartPlantTree(i + 1, j + 1, A, S1, S2, newNode); // pass the newNode as a leaf of the tree
            }
            else{ // not matching at the corner
                System.out.printf("(%d , %d )  ", i, j);
                System.out.println("No hits at the corner. ");
                A[i][j] += -7;

                // Search downward.
                int iGoDown = i+1; // start from the element downward
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
                    else{  // no matching in this column
                        A[iGoDown][j] += -1;
                        System.out.printf("(%d , %d~)  ", iGoDown - 1, j);
                        System.out.println("No hit in column");
                    }
                    iGoDown++;
                }
                // Search rightward.
                int jGoRight = j + 1; // one element rightward
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
                    else{  // no matching in this row
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
            // index out of bound, do nothing
            System.out.printf("(%d , %d )  ", i, j);
            System.out.println("i or j is out of index");
        }
    }
    // print along a tree
    // TODO: find the longest string along the tree
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
        printAlongNodes(head);  // the tree has been attached to `head`
        
        System.out.println();
        printChart(A, S1, S2);
    }
        
    public static void main(String[] args) {
        
        // test sample #1
        char[] S1 = {'A', 'X', 'M', 'G', 'J', 'K', 'C', '4', 'A', 'N', 'B'};
        char[] S2 = {'A', '$', 'C', '4', 'I', 'Q', 'M', 'A', '*', 'M', 'N'};
        
        // test sample #2
        //char[] S1 = {'A','$','C','M','A','*','M','N'};
        //char[] S2 = {'A','X','M','C','4','A','N','B'};
        
        LCS(S1, S2); // choose the longer as S1
        
        System.out.println("The end.");
    }
}
