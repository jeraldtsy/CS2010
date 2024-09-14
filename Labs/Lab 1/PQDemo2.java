import java.util.*;

public class PQDemo2 {

    public static void main(String[] args) {
        PriorityQueue<MyObject2> pq = new PriorityQueue<>((a, b) -> { //you define the comparator for the MyObject here
            if (a.first == b.first) {
                return a.second - b.second; // 'first' will be in increasing order
            } else {
                return a.first - b.first; // if 'first' is equal, 'second' will be in inrcreasing order
            }
        });
        
        pq.offer(new MyObject2(30, 3));
        pq.offer(new MyObject2(30, 2));
        pq.offer(new MyObject2(40, 1));
        pq.offer(new MyObject2(50, 5));
        pq.offer(new MyObject2(30, 4));
        
        System.out.println(pq.toString()); // not necessarily sorted, but the frontmost element must be correct
        
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // poll will return the value in the root and perform extractMax
        }
    }
}

class MyObject2 {
    public int first, second; //attributes

    public MyObject2(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" + first + "," + second + ")";
    }
}