package jdk7_myhashmap;

public class MyHashMap<K, V> implements MyMap<K, V> {

    //1.define default container
    Node<K, V>[] table = null;
    //2.container size
    int size;
    //3.load factor
    float DEFAULT_LOAD_FACTOR = 0.75f;
    //table default size
    static int DEFAULT_INITAL_CAPACITY = 16;

    @Override
    public V put(K key, V value) {
        if (table == null) {
            table = new Node[DEFAULT_INITAL_CAPACITY];
        }
        //resize
        if (size >= (DEFAULT_INITAL_CAPACITY * DEFAULT_INITAL_CAPACITY)) {
            resize();
        }
        int index = getIndex(key, DEFAULT_INITAL_CAPACITY);
        Node<K, V> node = table[index];
        if (node == null) {
            table[index] = new Node<>(key, value, null);
            size++;
            return table[index].getValue();
        } else {
            Node<K, V> newNode = node;
            while (newNode != null) {
                if (newNode.getKey().equals(key) || newNode.getKey() == key) {
                    return newNode.setValue(value);
                }
                newNode = newNode.getNext();
            }
            //加在头部
            table[index] = new Node<>(key, value, node);
            size++;
            return table[index].getValue();
        }
    }


    public void print() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            System.out.print("下标位置[" + i + "]");
            while (node != null) {
                System.out.print("[key:" + node.getKey() + ",value:" + node.getValue() + "]");
                node = node.getNext();
            }
            System.out.println();
        }
    }

    public void resize() {
        Node<K,V>[] newTable = new Node[DEFAULT_INITAL_CAPACITY << 1];
        for (int i = 0; i < table.length; i++) {
            Node<K,V> oldNode = table[i];
            while (oldNode != null) {
                table[i] = null;
                K oldKey = oldNode.getKey();
                int index = getIndex(oldKey, newTable.length);
                Node<K,V> oldNext = oldNode.next;
                oldNode.next = newTable[index];
                newTable[index] = oldNode;
                oldNode = oldNext;
            }
        }
        table = newTable;
        DEFAULT_INITAL_CAPACITY = newTable.length;
    }


    private int getIndex(K k, int length) {
        return k.hashCode() % length;
    }

    @Override
    public V get(K k) {
        Node<K, V> node = getNode(table[getIndex(k, DEFAULT_INITAL_CAPACITY)], k);
        return node == null ? null : node.getValue();
    }

    public Node<K, V> getNode(Node<K, V> node, K k) {
        while (node != null) {
            if (node.getKey().equals(k)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Node<K, V> implements Entry<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public Node<K, V> getNext() {
            return this.next;
        }
    }

}
