package com.example.ArrayListAndHashMapRealization.realizations;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;

public class ArrayListByIlya <E> extends AbstractList<E> implements List<E>, Serializable {

    private static final int defaultSize = 10;

    private Object[] array;

    private int size;

    public ArrayListByIlya() {
        this.array = new Object[10];
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
        if(size == array.length){
            Object[] ourNewArray = new Object[array.length + 10];
            for(int i = 0; i < size; i++){
                ourNewArray[i] = array[i];
            }
            ourNewArray[size] = item;
            this.array = ourNewArray;
            size++;
            return true;
        }
        else if(size < array.length){
            array[size] = item;
            size++;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean remove(Object item){
        int removeIndex = indexOf(item);
        
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
}
