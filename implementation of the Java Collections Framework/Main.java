import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        // Test LinkedList
        LinkedListTest.main(args);

        // Test ArrayList
        ArrayListTest.main(args);

        // Test Stack
        StackTest.main(args);

        // Test Queue
        QueueTest.main(args);

        // Test HashTable
        HashTableTest.main(args);
    }
    
    // LinkedList Implementation
    public static class LinkedList<E> {
        private Node<E> head;
        private Node<E> tail;
        private int size;

        private static class Node<E> {
            E data;
            Node<E> next;

            Node(E data) {
                this.data = data;
            }
        }

        public void add(E element) {
            Node<E> newNode = new Node<>(element);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        public E remove() {
            if (head == null) {
                throw new NoSuchElementException();
            }
            E data = head.data;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return data;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    // LinkedList Test
    public static class LinkedListTest {
        public static void main(String[] args) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            System.out.println("LinkedList Size: " + list.size()); // Output: Size: 3

            while (!list.isEmpty()) {
                System.out.println("LinkedList Removed: " + list.remove());
            }
        }
    }

    // ArrayList Implementation
    public static class ArrayList<E> {
        private static final int INITIAL_CAPACITY = 10;
        private E[] data;
        private int size;

        public ArrayList() {
            data = (E[]) new Object[INITIAL_CAPACITY];
            size = 0;
        }

        public void add(E element) {
            if (size == data.length) {
                resize();
            }
            data[size++] = element;
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return data[index];
        }

        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            E element = data[index];
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            data[--size] = null;
            return element;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void resize() {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    // ArrayList Test
    public static class ArrayListTest {
        public static void main(String[] args) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            System.out.println("ArrayList Size: " + list.size()); // Output: Size: 3

            for (int i = 0; i < list.size(); i++) {
                System.out.println("ArrayList Element at " + i + ": " + list.get(i));
            }

            System.out.println("ArrayList Removed: " + list.remove(1)); // Output: Removed: 2
        }
    }

    // Stack Implementation
    public static class Stack<E> {
        private LinkedList<E> list = new LinkedList<>();

        public void push(E element) {
            list.add(element);
        }

        public E pop() {
            if (list.isEmpty()) {
                throw new NoSuchElementException();
            }
            return list.remove();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int size() {
            return list.size();
        }
    }

    // Stack Test
    public static class StackTest {
        public static void main(String[] args) {
            Stack<Integer> stack = new Stack<>();
            stack.push(1);
            stack.push(2);
            stack.push(3);

            System.out.println("Stack Size: " + stack.size()); // Output: Size: 3

            while (!stack.isEmpty()) {
                System.out.println("Stack Popped: " + stack.pop());
            }
        }
    }

    // Queue Implementation
    public static class Queue<E> {
        private LinkedList<E> list = new LinkedList<>();

        public void enqueue(E element) {
            list.add(element);
        }

        public E dequeue() {
            if (list.isEmpty()) {
                throw new NoSuchElementException();
            }
            return list.remove();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int size() {
            return list.size();
        }
    }

    // Queue Test
    public static class QueueTest {
        public static void main(String[] args) {
            Queue<Integer> queue = new Queue<>();
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);

            System.out.println("Queue Size: " + queue.size()); // Output: Size: 3

            while (!queue.isEmpty()) {
                System.out.println("Queue Dequeued: " + queue.dequeue());
            }
        }
    }

    // HashTable Implementation
    public static class HashTable<K, V> {
        private static final int INITIAL_CAPACITY = 16;
        private LinkedList<Entry<K, V>>[] table;
        private int size;

        private static class Entry<K, V> {
            K key;
            V value;

            Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        public HashTable() {
            table = new LinkedList[INITIAL_CAPACITY];
        }

        public void put(K key, V value) {
            int index = hash(key);
            if (table[index] == null) {
                table[index] = new LinkedList<>();
            }
            for (Entry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
            table[index].add(new Entry<>(key, value));
            size++;
        }

        public V get(K key) {
            int index = hash(key);
            if (table[index] != null) {
                for (Entry<K, V> entry : table[index]) {
                    if (entry.key.equals(key)) {
                        return entry.value;
                    }
                }
            }
            return null;
        }

        public V remove(K key) {
            int index = hash(key);
            if (table[index] != null) {
                for (Entry<K, V> entry : table[index]) {
                    if (entry.key.equals(key)) {
                        V value = entry.value;
                        table[index].remove(entry);
                        size--;
                        return value;
                    }
                }
            }
            return null;
        }

        public int size() {
            return size;
        }

        private int hash(K key) {
            return key.hashCode() % table.length;
        }
    }

    // HashTable Test
    public static class HashTableTest {
        public static void main(String[] args) {
            HashTable<String, Integer> table = new HashTable<>();
            table.put("One", 1);
            table.put("Two", 2);
            table.put("Three", 3);

            System.out.println("HashTable Size: " + table.size()); // Output: Size: 3

            System.out.println("HashTable Get 'Two': " + table.get("Two")); // Output: Get 'Two': 2

            System.out.println("HashTable Removed 'Two': " + table.remove("Two")); // Output: Removed 'Two': 2
            System.out.println("HashTable Get 'Two' after removal: " + table.get("Two")); // Output: Get 'Two' after removal: null
        }
    }
}
