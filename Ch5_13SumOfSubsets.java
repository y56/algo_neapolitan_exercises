public class Ch5_13 {
  static void printIntArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
    }
  }

  static void function(int i, int totalOfTaken, int totalOfUntaken, int[] include, int[] w, int target) {
    System.out.print(i+"       "+totalOfTaken+"       "+totalOfUntaken+"       ");
    printIntArray(include);

    if (promising(i, totalOfTaken, totalOfUntaken, w, target)) {
      if (totalOfTaken == target) { // promising and already meet
        System.out.print("     !!!~~~ MEET ~~~!!!     ");
          for (int j = 0; j <= i; j++) {
            System.out.print(include[j]);
          }
          System.out.println();
      }
      else { // promising, yet meet, and, the next items will not exceed the target
        System.out.println("     still promising");

        // go try on as "to take" and "not to take"
        // for yes, take i
        include[i + 1] = 1;
        for (int j = i + 2; j < w.length; j++) { // erase the yes/no records of previous branches
          include[j] = 0;
        }
        function(i + 1, totalOfTaken + w[i + 1], totalOfUntaken - w[i + 1],include, w, target);
        // for no, don't take it
        include[i + 1] = 2;
        for (int j = i + 2; j < w.length; j++) { // erase the yes/no records of previous branches
          include[j] = 0;
        }
        function(i + 1, totalOfTaken, totalOfUntaken - w[i + 1],include, w, target);
      }
    }
    else {
      System.out.print("      Not promising");
      if (totalOfTaken + totalOfUntaken < target) {
        System.out.print(" (totalOfTaken+totalOfUntaken<target)");
      }
      if ( i + 1 < w.length) {
        if (totalOfTaken != target && totalOfTaken + w[i + 1] > target)
          System.out.print("  Not meet, also, adding w[" + (i + 1) + "]=" + w[i + 1] + " will exceed");
        System.out.println();
      }
      else {
        if (totalOfTaken != target) {
          System.out.println("We have come to the end. Usage of all items are decided");
        }
      }
    }
  }
  static boolean promising(int i, int totalOfTaken, int totalOfUntaken, int[] w, int target) {
    if ( i + 1 < w.length) { // make sure i+1 is in the bound
      return (totalOfTaken + totalOfUntaken >= target) // still having chance to meet the target within those untaken
              // some of items in w[] are in neither totalOfTaken or totalOfUntaken because they are discarded in  midway
              && (totalOfTaken == target || totalOfTaken + w[i + 1] <= target);
              // meet the target OR still below the target
    }
    else { // deal with edge case when i+1 out of bound
      return (totalOfTaken == target);
              // at the ending edge, no next item, only check if meeting the target
    }
  }
  public static void main(String[] args) {
    int target = 52; // my target for sum-up
    int[] w = {2, 10, 13, 17, 22, 42}; // my candidates
    int[] include = new int[w.length]; // to record take(1) or not(0)
    // start from totalOfUntaken as sum of all items in w[]
    int totalOfUntaken = 0;
    for (int i = 0; i < w.length; i++) {
      totalOfUntaken += w[i];
    }
    System.out.println("i,totalOfTaken,totalOfUntaken,1(take)/2(not take)");
    function(-1, 0, totalOfUntaken, include, w, target);
  }
}
