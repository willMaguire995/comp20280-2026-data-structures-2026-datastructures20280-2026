package project20280.stacksqueues;

import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;

public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> ll;

    public static void main(String[] args) {
    }

    public LinkedStack() {
        // TODO
        ll = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) {
        // TODO
        ll.addLast(e);
    }

    @Override
    public E top() {
        // TODO
        return ll.last();
    }

    @Override
    public E pop() {
        // TODO
        return ll.removeLast();
    }

    public String toString() {
        String listString = ll.toString();
        String stackString = "";
        String half1;
        String half2;
        int i;

        //reverses string and strips ',' and '[]' from the string
        for(i = 1; i < listString.length()-1; i++){

            if(listString.charAt(i) == ','){

            }
            else{
                stackString = listString.charAt(i) + stackString;
            }
        }

        //adds non-numeric characters back to string
        stackString = stackString.replace(" ", ", ");
        stackString = stackString + "]";
        stackString = "[" + stackString;

        return stackString;
    }
}
