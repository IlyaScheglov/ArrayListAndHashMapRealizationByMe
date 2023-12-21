package com.example.ArrayListAndHashMapRealization.obj;

import java.io.Serializable;
import java.util.Map;

public class KeyAndValueObject<K, V> implements Serializable, Map.Entry<K, V> {

    private K key;

    private V value;

    public KeyAndValueObject(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public K setKey(K key) {
        this.key = key;
        return key;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }
}
