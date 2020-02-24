import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {



   private Node first,last;
   private int size=0;

    // construct an empty deque
    public Deque(){
    }

    private class Node{
        Item item;
        Node next;
        Node previous;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size==0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        Node oldFirst;
        //check if link list is empty
checkItemNotNull(item);
            oldFirst=first;
            first=new Node();
            first.item=item;
            first.next=oldFirst;
        if(size==0) last=first;
        else oldFirst.previous=first;
        size++;


    }

    private void checkItemNotNull(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // add the item to the back
    public void addLast(Item item){
Node oldLast;
        checkItemNotNull(item);
            oldLast=last;
            last=new Node();
            last.item=item;
            last.previous=oldLast;
            if (size>0) oldLast.next=last;
            else first=last;
          size++;


    }

    // remove and return the item from the front
    public Item removeFirst(){

        checkLinkedListEmpty();
        Item item=first.item;
    if(size>1){
        first=first.next;
        first.previous=null;
    }else {
        first=null;
        last=null;
    }
    size--;

        return item;
    }

    private void checkLinkedListEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    // remove and return the item from the back
    public Item removeLast(){

        checkLinkedListEmpty();
        Item item=last.item;
        if (size>1){
            last=last.previous;
            last.next=null;

        }else {
            first=null;
            last=null;
        }
        size--;
        return item;
    }



    @Override
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }


    private class ItemIterator implements Iterator<Item> {

        private Node current=first;
        @Override
        public boolean hasNext() {
            return current!=first;
        }



        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item value=current.item;
            current=current.next;
            return value;
        }


        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }
}
