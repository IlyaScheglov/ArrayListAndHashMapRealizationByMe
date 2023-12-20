package com.example.ArrayListAndHashMapRealization.realizations;

import com.example.ArrayListAndHashMapRealization.obj.KeyAndValueObject;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Function;

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

    //I should edit down code!!!
    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return super.getOrDefault(key, defaultValue);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        super.replaceAll(function);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return super.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return super.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return super.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return super.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return super.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return super.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return super.merge(key, value, remappingFunction);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
