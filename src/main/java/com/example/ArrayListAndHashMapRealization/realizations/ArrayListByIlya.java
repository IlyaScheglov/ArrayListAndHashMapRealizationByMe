package com.example.ArrayListAndHashMapRealization.realizations;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;

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

        checkLengthOfArray();
        for(int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = item;
        size++;
    }

    private void checkLengthOfArray(){
        if(size == array.length){
            Object[] ourNewArray = new Object[array.length + defaultSize];
            System.arraycopy(array, 0, ourNewArray, 0, size);
            array = ourNewArray;
        }
        else if(size > array.length){
            throw new RuntimeException("Something goes wrong wrong with ArrayList");
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean remove(Object item){
        int removeIndex = indexOf(item);
        remove(removeIndex);
        return true;
    }

    @Override
    public E remove(int index){
        E removedElement = (E) array[index];
        for(int i = index; i < size - 1; i++){
            array[i] = array[i + 1];
        }
        size--;
        System.arraycopy(array, 0, array, 0, size);
        return removedElement;
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
        if(resultIndex == -1){
            try {
                throw new Exception("Element not found!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            return resultIndex;
        }
    }

    public void clear(){
        array = new Object[defaultSize];
        size = 0;
    }

}
