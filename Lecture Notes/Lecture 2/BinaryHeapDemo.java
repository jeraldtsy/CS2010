import java.util.*;

class BinaryHeap {
  private ArrayList<Integer> A;
  private int BinaryHeapSize;

  BinaryHeap() {
    A = new ArrayList<Integer>();
    A.add(0); // dummy
    BinaryHeapSize = 0;
  }

  BinaryHeap(int[] array) {
    CreateHeap(array);
  }

  int parent(int i) { return i>>1; } // shortcut for i/2, round down
  
  int left(int i) { return i<<1; } // shortcut for 2*i
  
  int right(int i) { return (i<<1) + 1; } // shortcut for 2*i + 1
  
  void shiftUp(int i) {
    while (i > 1 && A.get(parent(i)) < A.get(i)) {
      int temp = A.get(i);
      A.set(i, A.get(parent(i)));
      A.set(parent(i), temp);
      i = parent(i);
    }
  }

  void Insert(int key) {
    BinaryHeapSize++;
    if (BinaryHeapSize >= A.size())
      A.add(key);
    else
      A.set(BinaryHeapSize, key);
    shiftUp(BinaryHeapSize);
  }

  void shiftDown(int i) {
    while (i <= BinaryHeapSize) {
      int maxV = A.get(i), max_id = i;
      if (left(i) <= BinaryHeapSize && maxV < A.get(left(i))) { // compare value of this node with its left subtree, if possible
        maxV = A.get(left(i));
        max_id = left(i);
      }
      if (right(i) <= BinaryHeapSize && maxV < A.get(right(i))) { // now compare with its right subtree, if possible
        maxV = A.get(right(i));
        max_id = right(i);
      }
  
      if (max_id != i) {
        int temp = A.get(i);
        A.set(i, A.get(max_id));
        A.set(max_id, temp);
        i = max_id;
      }
      else
        break;
    }
  }
  
  int ExtractMax() {
    int maxV = A.get(1);    
    A.set(1, A.get(BinaryHeapSize));
    BinaryHeapSize--; // virtual decrease
    shiftDown(1);  
    return maxV;
  }
  
  void CreateHeapSlow(int[] arr) { // the O(N log N) version, array arr is 0-based
    A = new ArrayList<Integer>();
    A.add(0); // dummy, this BinaryHeap is 1-based
    for (int i = 0; i < arr.length; i++)
      Insert(arr[i]);
  }
  
  void CreateHeap(int[] arr) { // the O(N) version, array arr is 0-based
    BinaryHeapSize = arr.length;
    A = new ArrayList<Integer>();
    A.add(0); // dummy, this BinaryHeap is 1-based
    for (int i = 0; i < BinaryHeapSize; i++) // copy the content
      A.add(arr[i]);
    for (int i = parent(BinaryHeapSize); i >= 1; i--)
      shiftDown(i);
  }

  ArrayList<Integer> HeapSort(int[] arr) { // array arr is 0-based
    CreateHeap(arr);
    int N = arr.length;
    for (int i = 1; i <= N; i++)
      A.set(N-i+1, ExtractMax());
    return A; // ignore the first index 0
  }

  int size() { return BinaryHeapSize; }
  
  boolean isEmpty() { return BinaryHeapSize == 0; }
}



class BinaryHeapDemo {
  public static void main(String[] args) {
    int[] array = new int[]{19, 3, 17, 1, 2, 25, 7, 36, 90};
    BinaryHeap h = new BinaryHeap();
      
    ArrayList<Integer> sorted = h.HeapSort(array); // build BinaryHeap routine is already inside HeapSort
    // first item will always be 0 (remember that we ignore index 0 in our BinaryHeap implementation!)
    for (int i = 0; i < sorted.size(); i++)
      System.out.println(sorted.get(i));    
    
    h.CreateHeapSlow(array); // rebuild BinaryHeap again, we will get the same BinaryHeap, just slightly slower
    System.out.println("-------------");
    System.out.println(h.ExtractMax()); // must be 90
    System.out.println(h.ExtractMax()); // must be 36 now
    
    h.CreateHeap(array); // rebuild BinaryHeap again, using different technique (faster)
    System.out.println("-------------");
    System.out.println(h.ExtractMax()); // must be 90
    System.out.println(h.ExtractMax()); // must be 36
    h.Insert(24);
    System.out.println(h.ExtractMax()); // must be 25
    System.out.println(h.ExtractMax()); // must be 24, the one that we inserted recently
  }
}
