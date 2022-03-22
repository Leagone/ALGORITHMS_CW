package com.company;

/*
 W1780616
 Andrzej Baum
 5SE01
*/

public class MyQueue {

    private final Node[] queue;
    private final int capacity;

    private int head;
    private int tail;
    private int size;

    public MyQueue(int capacity) {
        queue = new Node[capacity];
        this.capacity = capacity;
        size = 0;
        head = 0;
        tail = -1;
    }

    public void enqueueAll(Node node) {
        for (int i = 0; i < node.adjacency.size(); i++) {

            enqueue(node.adjacency.get(i));

        }
    }

    public void enqueue(Node data) {
        if (isFull()) {
            System.out.println("Queue Full");
        } else {

            tail = (tail + 1) % capacity;
            queue[tail] = data;
            size++;
        }

    }


    public Node dequeue() {
        if (isEmpty()) {
            System.out.println("Queue empty");
            return null;
        } else {

            Node NodeToRemove = queue[head];
            head = (head + 1) % capacity;
            size--;
            return NodeToRemove;
        }

    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {

        return size == 0;

    }

}
