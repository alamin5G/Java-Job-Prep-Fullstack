import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Queue - FIFO
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.poll();  // "First"

// PriorityQueue - Min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(2);
        pq.offer(8);
        pq.poll();  // 2 (smallest)

// Deque - Double-ended queue
        Deque<String> deque = new ArrayDeque<>();
        deque.offerFirst("Front");
        deque.offerLast("Back");
        deque.add("Middle");
        System.out.println(deque);  // [Front, Back, Middle]
        System.out.println(deque.poll());  // "Front"
        System.out.println(deque.pollLast());  // "Back"
        deque.offerFirst("End");
        System.out.println(deque);


    }
}