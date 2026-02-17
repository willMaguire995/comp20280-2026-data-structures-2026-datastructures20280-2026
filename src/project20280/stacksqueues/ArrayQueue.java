package project20280.stacksqueues;

import project20280.interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private int front = 0;
    private int rear = 1;
    private int size = 0;

    public ArrayQueue(int capacity) {
        // TODO
        data = (E[]) new Object[capacity];

    }

    public ArrayQueue() {
        this(CAPACITY);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        // TODO
        if(size < CAPACITY) {
            if (size() == 0) {
                data[0] = e;
                size++;
            } else {
                data[rear] = e;
                size++;
                rear++;
            }
        }
        else{
            System.out.println("Out of bounds exception");
        }
    }

    @Override
    public E first() {
        return isEmpty() ? null : data[front];
    }

    @Override
    public E dequeue() {
        // TODO
        E temp;
        if(size < 2){
            temp = data[front];
            front=0;
            rear = 1;
            size--;
            return temp;
        }
        else{
            temp = data[front];
            front++;
            size--;
            if(rear == CAPACITY)rear = 0;
            if(front == CAPACITY) front = 0;
            return temp;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; ++i) {
            E res = data[(front + i) % CAPACITY];
            sb.append(res);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> qq = new ArrayQueue<>();
        System.out.println(qq);

        int N = 10;
        for (int i = 0; i < N; ++i) {
            qq.enqueue(i);
        }
        System.out.println(qq);

        for (int i = 0; i < N / 2; ++i) qq.dequeue();
        System.out.println(qq);

    }
}
