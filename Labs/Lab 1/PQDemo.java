import java.util.*;

class PQDemo {

    public static void main(String[] args) {
        PriorityQueue<MyObject> pq = new PriorityQueue<>(); // as the comparator is already defined in the MyObject class, you don't need to specify it here
        pq.offer(new MyObject(30, 3));
        pq.offer(new MyObject(30, 2));
        pq.offer(new MyObject(40, 1));
        pq.offer(new MyObject(50, 5));
        pq.offer(new MyObject(30, 4));
        
        System.out.println(pq.toString()); // this will output the heapified array, remember that binary heap uses array implementation
        
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // poll will return the value in the root and perform extractMax
        }
    }
}

class MyObject implements Comparable<MyObject> {
    public int first, second; //attributes

    public MyObject(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(MyObject o) {
        if (first != o.first) {
            return this.first - o.first; // 'first' will be in increasing order
        } else {
            return this.second - o.second; // if 'first' is equal, 'second' will be in inrcreasing order
        }
    }

    public String toString() {
        return "(" + first + "," + second + ")";
    }
}
