import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int N;

    // construct an empty randomized queue

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {

        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        autoEnlarge();
        a[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        checkNotEmpty();
        int index = randomIndex();
        Item item = a[index];
        a[index] = a[N - 1];
        a[N - 1] = null;
        N--;
        autoShrink();
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {

        checkNotEmpty();
        return a[randomIndex()];
    }

    private void checkNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private int randomIndex() {
        return StdRandom.uniform(0, N);
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }


    private void resize(int capacity) {
        assert capacity >= N;

         Item[] newArray = Arrays.copyOfRange(a, 0, capacity);
        a = newArray;
    }

    private void autoEnlarge() {
        if (N == a.length) resize(2 * a.length);
    }

    private void autoShrink() {
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private Item[] r;
        private int i;

        public RandomArrayIterator() {
            copyQueue();
            StdRandom.shuffle(r);
        }


        private void copyQueue() {

            Item[] r = Arrays.copyOfRange(a, 0, N);

        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return r[i++];
        }
    }
}
