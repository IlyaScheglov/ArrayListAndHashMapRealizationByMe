package com.example.ArrayListAndHashMapRealization.realizations;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayListByIlya <E> extends AbstractList<E> implements List<E>, Serializable {

    private static final int defaultSize = 10;

    private Object[] array;

    private int size;

    public ArrayListByIlya() {
        this.array = new Object[defaultSize];
        this.size = 0;
    }

    @Override
    public E get(int index) {
        if(index < size && index >= 0) {
            return (E) array[index];
        } else{
            throw new ArrayIndexOutOfBoundsException("Entered index is not correct!");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E item){
        try {
            add(size, item);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void add(int index, E item){
        if(index > size){
            throw new IllegalArgumentException("Index of new element can not be greater then size of ArrayList!");
        }
        if(size + 1 > array.length){
            checkLengthOfArray(1);
        }
        for(int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = item;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int cSize = c.size();
        if(index > size){
            throw new IllegalArgumentException("Index of new element can not be greater then size of ArrayList!");
        }

        int arrLengthNow = array.length;
        int arrLengthNeed = size + cSize;
        while(arrLengthNow < arrLengthNeed){
            checkLengthOfArray(cSize);
            arrLengthNow = array.length;
        }

        for(int i = size - 1; i >= index; i--){
            array[i + cSize] = array[i];
        }
        AtomicInteger localIndex = new AtomicInteger(index);
        c.forEach(col -> {
            array[localIndex.get()] = col;
            localIndex.getAndIncrement();
        });
        size += cSize;
        return true;
    }

    private void checkLengthOfArray(int countAddingElements){
            Object[] ourNewArray = new Object[array.length + defaultSize];
            System.arraycopy(array, 0, ourNewArray, 0, size);
            array = ourNewArray;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object item){
        int removeIndex = indexOf(item);
        if(removeIndex == -1){
            throw new NoSuchElementException("Element not found!");
        }
        remove(removeIndex);
        return true;
    }

    @Override
    public E remove(int index){
        if(size == array.length) System.arraycopy(array, 0, array, 0, size + 1);
        if(index < 0 || index > size - 1) throw new NoSuchElementException("Element not found!");

        E removedElement = (E) array[index];
        for(int i = index; i < size - 1; i++){
            array[i] = array[i + 1];
        }
        size--;
        System.arraycopy(array, 0, array, 0, size);
        return removedElement;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean collectionIsInArray = false;
        int indexOfBeginningCollection = 0;
        int indexOfEndingCollection = 0;
        for(int i = 0; i < size; i++){
            if(array[i].equals(c.toArray()[0]) && size >= i + c.size()){
                boolean check = true;
                int j;
                for( j = 0 ; j < c.size(); j++){
                    if(!c.toArray()[j].equals(array[i + j])){
                        check = false;
                        break;
                    }
                }
                if(check == true){
                    collectionIsInArray = check;
                    indexOfBeginningCollection = i;
                    indexOfEndingCollection = j;
                    break;
                }
            }
        }

        if(collectionIsInArray == false){
            throw new NoSuchElementException("Collection not found!");
        }

        for(int k = indexOfEndingCollection; k < size - 1; k++){
            array[k - c.size()] = array[k];
        }
        size -= c.size();
        System.arraycopy(array, 0, array, 0, size);
        return true;
    }

    @Override
    public int indexOf(Object item){
        E correctItem = (E) item;
        int resultIndex = -1;

        for(int i = 0; i < size; i++){
            if(array[i].equals(correctItem)){
                resultIndex = i;
            }
        }
        return resultIndex;
    }

    @Override
    public void clear(){
        array = new Object[defaultSize];
        size = 0;
    }

    public E[] toArray(){
        Object[] listInArrayForm = new Object[size];
        for(int i = 0; i < size; i++){
            listInArrayForm[i] = array[i];
        }
        return (E[]) listInArrayForm;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for(int i = 0; i < size; i++){
            joiner.add(String.valueOf(array[i]));
        }
        return joiner.toString();
    }
}
