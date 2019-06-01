public class Main {
  static void printIntArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
    } 
    System.out.println();
  }
  
  static void f(int i, int weight, int total, int[] include, int[] w, int myW) {
    if (promising(i, weight, total, w, myW)) {
      printIntArray(include); 
    }
    else {
      include[i + 1] = 1; // for yes
      f(i + 1, weight + w[i + 1], total - w[i + 1],include, w, myW);
      include[i + 1] = 2; // for no
      f(i + 1, weight, total - w[i + 1],include, w, myW);
    }
    
  }
  static boolean promising(int i, int weight, int total, int[] w, int myW) {
    return (weight + total >= myW) && (weight == myW || weight + w[i + 1] <= myW);
  }
	public static void main(String[] args) {
    int myW = 52;
    int[] w = {2,10,13,17,22,42};
    int[] include = new int[w.length];
    f(0, 0, 0, include, w, myW);
	}
}
