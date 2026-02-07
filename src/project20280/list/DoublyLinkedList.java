package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private  Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setNext(Node<E> n){
            next = n;
        }

        public void setPrev(Node<E> p){
            prev = p;
        }

    }

    private final Node<E> head;
    private final Node<E> tail;
    private final int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, null, null);
        //head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
    }

    @Override
    public int size() {
        if(head.next == null){
            return 0;
        }
        else{
            int size = 0;
            Node<E> currPos = head;
            while(currPos != null){
                size++;
                currPos = currPos.getNext();
            }
            return size - 2;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public E get(int i) {
        //TODO
        int parser;
        Node<E> temp;
        Node<E> prevNode;
        if(isEmpty()){
            return null;
        }
        else if(size() < i){
            System.out.println("Needs exception. i exceeds size of list");
            return null;
        }
        else{
            parser = 0;
            temp = head;


            while(parser != i+1){
                parser++;
                temp = temp.getNext();
            }

            return temp.getData();

        }
    }


    @Override
    public void add(int i, E e) {
        //TODO
        Node<E> currNode;
        Node<E> prevNode;
        Node<E> newNode;
        int parser;

        if(isEmpty()){
            addFirst(e);
        }
        else{
            currNode = head;
            prevNode = null;
            parser = 0;
            while(parser != i+1){
                prevNode = currNode;
                currNode = currNode.getNext();
                parser++;
                System.out.println("loop: " + parser + "\tData: " + currNode.getData());
            }
            System.out.println("Temp: " + currNode.getData() + "\ttmep prev: " + prevNode.getData());
            newNode = new Node<>(e, prevNode, currNode);
            prevNode.setNext(newNode);
            currNode.setPrev(newNode);
        }
    }

    @Override
    public E remove(int i) {
        //TODO
        E e = null;
        if(isEmpty()){
           return e;
        }
        else if(size() < i){
            System.out.println("List is not that big");
        }
        else{
            int currPos = 0;
            Node<E> currNode = head;
            Node<E> prevNode = null;
            //need to review logic of condition to why it needs +1
            while(currPos != i+1){
                prevNode = currNode;
                currNode = currNode.getNext();
                currPos++;
            }
            if(prevNode != null){
                prevNode.setNext(currNode.getNext());
                currNode.getNext().setPrev(prevNode);
            }
            e = currNode.getData();
        }
        return e;
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        // TODO
        Node<E> prev;
        Node<E> next;
        if(isEmpty()){
            return null;
        }
        else{
            next = n .getNext();
            prev = n.getPrev();

            next.setPrev(prev);
            prev.setNext(next);
            return n.getData();
        }
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getNext().getData();
    }

    public E last() {
        if(isEmpty()){
            return null;
        }
        else{
            return tail.getPrev().getData();
        }
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        else{
            Node<E> temp = head.getNext();
            Node<E> newFirst = temp.getNext();
            newFirst.setPrev(head);
            head.setNext(newFirst);

            return temp.getData();
        }
    }

    @Override
    public E removeLast() {
        if(isEmpty()) {
            return null;
        }
        else{
            Node<E> temp = tail.getPrev();
            Node<E> newLast = temp.getPrev();
            newLast.setNext(tail);
            tail.setPrev(newLast);

            return temp.getData();
        }
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode;

        if(head.getNext() == null){
            newNode = new Node<>(e, head, tail);
            head.setNext(newNode);
            tail.setPrev(newNode);
        }
        else{
            Node<E> oldLast = tail.getPrev();
            newNode = new Node<>(e, oldLast, tail);
            oldLast.setNext(newNode);
            tail.setPrev(newNode);
        }
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode;

        if(head.getNext() == null){
            newNode = new Node<>(e, head, tail);
            head.setNext(newNode);
            tail.setPrev(newNode);
        }
        else{
            Node<E> oldFirst = head.getNext();
            newNode = new Node<>(e, head, oldFirst);
            head.setNext(newNode);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(3);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(-3);
        ll.addLast(-1);
        System.out.println(ll);

        //ll.removeFirst();
        System.out.println(ll);

        //ll.removeLast();
        System.out.println(ll);

        ll.add(2, 5);
        System.out.println(ll);

        ll.remove(2);
        System.out.println(ll);


        System.out.println("Size: " + ll.size());
        System.out.println("IsEmpty: " + ll.isEmpty());
        System.out.println("First: " + ll.first());
        System.out.println("Last: " + ll.last());
        System.out.println("Get: " + ll.get(2));

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}