package jdk7_myhashmap;

public interface MyMap<K, V> {

    V put(K k, V v);

    V get(K k);

    public int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);
    }

}
