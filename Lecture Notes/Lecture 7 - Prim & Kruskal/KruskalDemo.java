import java.util.*;
import java.io.*;

public class KruskalDemo {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    /*
    // Sample graph shown in lecture
    5 7
    1 2 4
    1 3 4
    2 3 2
    1 4 6
    3 4 8
    1 5 6
    4 5 9
    */

    // THE FIRST PART, DEMO OF USING EDGE LIST DATA STRUCTURE + SORTING EDGES
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt(), E = sc.nextInt();

    // another graph data structure: EdgeList
    ArrayList < IntegerTriple > EdgeList = new ArrayList < IntegerTriple >();
    for (int i = 0; i < E; i++) { // simply populate this EdgeList
      // we decrease index by 1 to change input to 0-based indexing
      int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
      EdgeList.add(new IntegerTriple(w, u, v)); // we store this information as (w, u, v)
    }

    System.out.println("BEFORE SORTING");
    for (int i = 0; i < E; i++)
      System.out.println(EdgeList.get(i));
    System.out.println("==============");

    // sort by edge weight O(E log E)
    Collections.sort(EdgeList);

    System.out.println("AFTER  SORTING");
    for (int i = 0; i < E; i++)
      System.out.println(EdgeList.get(i));
    System.out.println("==============");

    

    // THE SECOND PART, THE MAIN LOOP OF KRUSKAL'S ALGORITHM
    UnionFind UF = new UnionFind(V); // all V are disjoint sets at the beginning
    int i, mst_cost = 0;
    for (i = 0; i < E; i++) { // process all edges, O(E)
      IntegerTriple e = EdgeList.get(i);
      int u = e.second(), v = e.third(), w = e.first(); // note that we have re-ordered the integer triple
      if (!UF.isSameSet(u, v)) { // if no cycle
        mst_cost += w; // add weight w of e to MST
        System.out.println("Adding   edge: " + e + ", MST cost now = " + mst_cost);
        UF.unionSet(u, v); // link these two vertices
      }
      else
        System.out.println("Ignoring edge: " + e + ", MST cost now = " + mst_cost);
    }
    
    System.out.printf("Final MST cost %d\n", mst_cost);
  }
}



class IntegerTriple implements Comparable<IntegerTriple> {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return this.third() - o.third();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }

  public String toString() { return first() + " " + second() + " " + third(); }
}



// Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind {                                              // OOP style
  private ArrayList<Integer> p, rank, setSize;
  private int numSets;

  public UnionFind(int N) {
    p = new ArrayList<Integer>(N);
    rank = new ArrayList<Integer>(N);
    setSize = new ArrayList<Integer>(N);
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
    }
  }

  public int findSet(int i) { 
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i)); p.set(i, ret);
      return ret; } }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { numSets--; 
    int x = findSet(i), y = findSet(j);
    // rank is used to keep the tree short
    if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
    else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y)+1); } } }
  public int numDisjointSets() { return numSets; }
  public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}
