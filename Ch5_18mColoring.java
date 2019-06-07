public class Ch5_18mColoring {
  static void print1DIntArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
    }
    System.out.print(" ");
  }
  static void mColoring(int i, int m, int[] vColor, boolean[][] adjacencyMatrix) {
    System.out.print("i=" + i);
    System.out.print(", coloring as: ");
    print1DIntArray(vColor);

    if (promising(i, m, vColor, adjacencyMatrix)) {
      if (i == vColor.length - 1) { // promising and this is the last node
        System.out.println("Finish!");
      }
      else { // promising, not yet meet the goal
        System.out.println("Promising, not the end, go on.");
        for (int color = 7; color < 7 + m; color++) { // do branching
          vColor[i + 1] = color;
          for (int j = i + 2; j < vColor.length; j++) {
            vColor[j] = 0;
          }
          mColoring(i + 1, m, vColor, adjacencyMatrix);
        }
      }
    }
  }
  static boolean promising(int i, int m, int[] vColor, boolean[][] adjacencyMatrix) {
    boolean flag = true;
    int j = 0;
    while (j < i && flag) {
      // Check if the colors of adjacent nodes are different or not
      if (adjacencyMatrix[i][j] && (vColor[i] == vColor[j])) { // I can't do && between int and bool,
        // so I turn adjacencyMatrix into boolean[][]
        System.out.println("Adjacent nodes with a same color. Go back.");
        flag = false;
      }
      j++;
    }
    return flag;
  }
  public static void main(String[] args) {

    boolean[][] adjacencyMatrix = new boolean[][] { // an adjacency matrix to present the graph
      {true,  true,  false, true,  false, false},
      {true,  true,  true,  false, true,  false},
      {false, true,  true,  false, false, true},
      {true,  false, false, true,  true,  false},
      {false, true,  false, true,  true,  true},
      {false, false, true,  false, true,  true}
    };
    int[] vColor = new int[adjacencyMatrix.length]; // the coloring result of each vertex
    int m = 3; // number of colors we can use
    mColoring(-1, m, vColor, adjacencyMatrix);
  }
}
