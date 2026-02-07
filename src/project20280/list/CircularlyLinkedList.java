package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private  Node<E> tail = null;
    private Node<E> head = null;
    private final int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return tail == null;
    }

    @Override
    public E get(int i) {
        int currPos = 0;
        Node<E> currNode = new Node<>(head.data, head.next);
        while(currPos != i){
            currNode = currNode.getNext();
            currPos++;
        }
        return currNode.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if(isEmpty()){
            System.out.println("The list is empty");
        }
        else if(size() < i){
            System.out.println("List is not that big");
        }
        else{
            int currPos = 0;
            Node<E> currNode = head;
            Node<E> prevNode = null;
            while(currPos != i){
                prevNode = currNode;
                currNode = currNode.getNext();
                currPos++;
            }
            Node<E> newNode = new Node<>(e, currNode);
            if(prevNode != null){
                prevNode.setNext(newNode);
            }
        }
    }

    @Override
    public E remove(int i) {
        E e = null;
        if(isEmpty()){
            System.out.println("The list is empty");
        }
        else if(size() < i){
            System.out.println("List is not that big");
        }
        else{
            int currPos = 0;
            Node<E> currNode = head;
            Node<E> prevNode = null;
            while(currPos != i){
                prevNode = currNode;
                currNode = currNode.getNext();
                currPos++;
            }
            if(prevNode != null){
                prevNode.setNext(currNode.getNext());
            }
            e = currNode.getData();
        }
        return e;
    }

    public void rotate() {
        Node<E> newFirst;
        Node<E> oldLast;
        if(isEmpty()){
            System.out.println("List is empty");
        }
        else if(size() == 1){
            System.out.println("Nothing to rotate, size is 1.");
        }
        else{
            newFirst = head.getNext();
            oldLast = tail;
            tail = head;
            head = newFirst;
            oldLast.setNext(tail);

        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        if(head == null){
            return 0;
        }
        else if(head == tail){
            return 1;
        }
        else{
            //size starts at one because head is already accounted for when node is initialised
            int size = 1;
            Node<E> currPos = head;
            while(currPos != tail){
                size++;
                currPos = currPos.getNext();
            }
            return size;
        }
    }

    @Override
    public E removeFirst() {
        E deletedData;
        Node<E> deletedNode;
        Node<E> secondNode;
        if(isEmpty()){
            return null;
        }
        else if(size() == 1){
            deletedData = head.getData();
            head = null;
            tail = null;
            return deletedData;
        }
        else{
            secondNode = head.getNext();
            deletedNode = head;
            deletedData = deletedNode.getData();

            tail.setNext(secondNode);
            head = secondNode;
            return deletedData;
        }
    }

    @Override
    public E removeLast() {
        E deletedData;
        Node<E> deletedNode;
        Node<E> traverse;
        if(isEmpty()){
            return null;
        }
        else if(size() == 1){
            deletedData = head.getData();
            head = null;
            tail = null;
            return deletedData;
        }
        else{
            traverse = head;
            deletedNode = tail;
            deletedData = deletedNode.getData();
            while(traverse.getNext() != tail){
                traverse = traverse.getNext();
            }
            traverse.setNext(head);
            tail = traverse;
            return deletedData;
        }
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode;
        Node<E> oldFirst;
        if(isEmpty()){
            newNode = new Node<>(e, tail);
            tail = newNode;
            head = newNode;
        }
        else{
            oldFirst = head;
            newNode = new Node<>(e, oldFirst);
            head = newNode;
            tail.setNext(head);
        }
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode;
        Node<E> oldLast;
        if(isEmpty()){
            newNode = new Node<>(e, tail);
            tail = newNode;
            head = newNode;
        }
        else{
            oldLast = tail;
            newNode = new Node<>(e, head);
            tail = newNode;
            oldLast.setNext(tail);
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        ll.addFirst(1);
        ll.addLast(-1);

        System.out.println("Size: " + ll.size());

        /*
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }



        System.out.println("1: " + ll);
        System.out.println("Get index 1: " + ll.get(1));
        ll.add(3, 24);
        System.out.println("Add 24 at index 3: " + ll);
        System.out.println("Remove index 2: " + ll.remove(2));

        ll.removeFirst();
        System.out.println("2: " + ll);

        ll.removeLast();
        System.out.println("3: " + ll);

        ll.rotate();
        System.out.println("4: " + ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println("5: " + ll);

        ll.removeLast();
        ll.rotate();
        System.out.println("6: " + ll);

         */

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
