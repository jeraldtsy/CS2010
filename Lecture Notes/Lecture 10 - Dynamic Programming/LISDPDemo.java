import java.util.*;

public class LISDPDemo {
  private static int[] A;
  private static ArrayList < Integer > memo = new ArrayList < Integer > ();
  private static int N, ans;

  private static int LIS(int i) {
    if (i == N-1) return 1;
    // if (memo.get(i) != -1) return memo.get(i); // uncomment this line to get major speed-up :) 

    int ans = 1; // at least A[i] itself
    for (int j = i+1; j < N; j++)
      if (A[i] < A[j]) // if can be extended
        ans = Math.max(ans, LIS(j)+1);
    memo.set(i, ans);
    return ans;
  }

  public static void main(String[] args) {
/*
    // LIS (using pure DP technique)
    N = 8;
    A = new int[]{-7, 10, 9, 2, 3, 8, 8, 1};

    N = 20;
    A = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    N = 25;
    A = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

    // corner case: the answer is 3 (1-2-3, starting from the second element), not 1 (starting from the first element only)
    N = 4;
    A = new int[]{100, 1, 2, 3};

    // stress test, if you have not removed the comment in function LIS, this part is very slow
    N = 30;
    A = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

    // the answer should be 31
    N = 61;
    A = new int[]{
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
*/

    N = 61;
    A = new int[]{
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
     
    memo.clear(); memo.addAll(Collections.nCopies(N, -1)); // to say 'not filled yet'

    ans = 0;
    for (int i = 0; i < N; i++) // get the max LIS
      ans = Math.max(ans, LIS(i));
    System.out.printf("LIS = %d\n", ans);
  }
}
