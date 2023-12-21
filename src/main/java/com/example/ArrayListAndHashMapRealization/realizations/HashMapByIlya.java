package com.example.ArrayListAndHashMapRealization.realizations;

import com.example.ArrayListAndHashMapRealization.obj.KeyAndValueObject;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HashMapByIlya<K, V> extends AbstractMap<K, V> implements Map<K, V>, Serializable {

    private static final int defaultLengthOfMapArray = 16;

    private LinkedList<KeyAndValueObject<K, V>>[] array;

    private int size;

    public HashMapByIlya() {
        this.array = new LinkedList[defaultLengthOfMapArray];
        this.size = 0;
        for(int i = 0; i < defaultLengthOfMapArray; i++){
            array[i] = new LinkedList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        KeyAndValueObject newObj = new KeyAndValueObject(key, value);
        long keyHash = newObj.getKey().hashCode();
        int indexInArray = (int) keyHash % defaultLengthOfMapArray;
        LinkedList<KeyAndValueObject<K, V>> checkList = array[indexInArray];

        checkList.forEach(cl -> {
            if(cl.getKey().hashCode() == keyHash){
                if(cl.getKey().equals(newObj.getKey())){
                    checkList.remove(cl);
                }
            }
        });
        checkList.add(newObj);
        size++;
        return (V) newObj.getValue();
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
    public V get(Object key) {
        var ref = new Object() {
            V value = null;
        };
        long keyHash = key.hashCode();
        int indexInArray = (int) keyHash % defaultLengthOfMapArray;
        LinkedList<KeyAndValueObject<K, V>> checkList = array[indexInArray];

        checkList.forEach(cl -> {
            if(cl.getKey().hashCode() == keyHash){
                if(cl.getKey().equals(key)){
                    ref.value = cl.getValue();
                }
            }
        });
        return ref.value;
    }

    @Override
    public boolean containsKey(Object key) {
        long keyHash = key.hashCode();
        int indexInArray = (int) keyHash % defaultLengthOfMapArray;
        LinkedList<KeyAndValueObject<K, V>> checkList = array[indexInArray];
        AtomicBoolean thereAreThisKey = new AtomicBoolean(false);

        checkList.forEach(cl -> {
            if(cl.getKey().hashCode() == keyHash){
                if(cl.getKey().equals(key)){
                    thereAreThisKey.lazySet(true);
                }
            }
        });
        return thereAreThisKey.get();
    }

    @Override
    public void clear() {
        array = new LinkedList[defaultLengthOfMapArray];
        size = 0;
        for(int i = 0; i < defaultLengthOfMapArray; i++){
            array[i] = new LinkedList<>();
        }
    }

    @Override
    public Collection<V> values() {
        LinkedList<V> result = new LinkedList<>();

        for(int i = 0; i < defaultLengthOfMapArray; i++){
            LinkedList<KeyAndValueObject<K, V>> checkList = array[i];
            checkList.forEach(cl -> result.add(cl.getValue()));
        }
        return result;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Entry<? extends K, ? extends V>> entrySet = m.entrySet();
        entrySet.forEach(es -> put(es.getKey(), es.getValue()));
    }

    @Override
    public V putIfAbsent(K key, V value) {
        long keyHash = key.hashCode();
        int indexInArray = (int) keyHash % 16;
        LinkedList<KeyAndValueObject<K, V>> checkList = array[indexInArray];
        AtomicBoolean thisElementIsPresent = new AtomicBoolean(false);

        checkList.forEach(cl -> {
            if(cl.getKey().hashCode() == keyHash){
                if(cl.getKey().equals(key)){
                    thisElementIsPresent.lazySet(true);
                }
            }
        });
        if(thisElementIsPresent.get() == true){
            return value;
        }
        else{
            checkList.add(new KeyAndValueObject<>(key, value));
            size++;
            return value;
        }
    }

    @Override
    public V remove(Object key) {
        Object[] valueToReturn = new Object[1];
        long keyHash = key.hashCode();
        int indexInArray = (int) keyHash % 16;
        LinkedList<KeyAndValueObject<K, V>> checkList = array[indexInArray];

        checkList.forEach(cl -> {
            if(cl.getKey().hashCode() == keyHash){
                if(cl.getKey().equals(key)){
                    valueToReturn[0] = cl.getValue();
                    checkList.remove(cl);
                    size--;
                }
            }
        });
        return (V) valueToReturn[0];
    }

    @Override
    public boolean remove(Object key, Object value) {
        AtomicBoolean result = new AtomicBoolean(false);
        long keyHash = key.hashCode();
        int indexInArray = (int) keyHash % 16;
        LinkedList<KeyAndValueObject<K, V>> checkList = array[indexInArray];

        checkList.forEach(cl -> {
            if(cl.getKey().hashCode() == keyHash){
                if(cl.getKey().equals(key) && cl.getValue().equals(value)){
                    checkList.remove(cl);
                    size--;
                    result.lazySet(true);
                }
            }
        });
        return result.get();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for(int i = 0; i < defaultLengthOfMapArray; i++){
            LinkedList<KeyAndValueObject<K, V>> checkList = array[i];
            checkList.forEach(cl -> joiner.add("[" + String.valueOf(cl.getKey()) +
                    ", " + String.valueOf(cl.getValue()) + "]"));
        }
        return joiner.toString();
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();

        for(int i = 0; i < defaultLengthOfMapArray; i++){
            LinkedList<KeyAndValueObject<K, V>> checkList = array[i];
            checkList.forEach(cl -> result.add(cl.getKey()));
        }
        return result;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> result = new HashSet<>();
        for(int i = 0; i < defaultLengthOfMapArray; i++){
            LinkedList<KeyAndValueObject<K, V>> checkList = array[i];
            checkList.forEach(cl -> result.add((Entry<K, V>) cl));
        }
        return result;
    }
}
