package com.example.ArrayListAndHashMapRealization.realizations;

import jdk.jfr.Unsigned;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayListByIlyaTest {

    private List<Integer> testList;

    @BeforeEach
    void setUp(){
        testList = new ArrayListByIlya<>();
    }

    @Test
    void sizeZero(){
        int testSize = testList.size();
        Assertions.assertEquals(0, testSize);
    }

    @Test
    void sizeNotZero(){
        testList.add(1);
        testList.add(2);
        int testSize = testList.size();
        Assertions.assertEquals(2, testSize);
    }

    @Test
    void sizeAfterRemove(){
        testList.add(1);
        testList.add(2);
        testList.remove(2);
        int testSize = testList.size();
        Assertions.assertEquals(1, testSize);
    }

    @Test
    void emptyTest(){
        boolean isItEmpty = testList.isEmpty();
        Assertions.assertTrue(isItEmpty);
    }

    @Test
    void notEmptyTest(){
        testList.add(1);
        boolean isItEmpty = testList.isEmpty();
        Assertions.assertFalse(isItEmpty);
    }

    @Test
    void addWithoutIndexTest(){
        testList.add(1);
        testList.add(2);
        Assertions.assertEquals(1, testList.get(0));
        Assertions.assertEquals(2, testList.get(testList.size() - 1));
        Assertions.assertEquals(2, testList.size());
    }

    @Test
    void addWithIndex(){
        testList.add(1);
        testList.add(2);
        testList.add(2, 5);
        Assertions.assertEquals(3, testList.size());
        Assertions.assertEquals(5, testList.get(testList.size() - 1));
    }


    @Test
    void addWithIndexErrorThrow(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                testList.add(1, 10));
    }

    @Test
    void addAllWithoutIndexTest(){
        List<Integer> addingList = new ArrayList<>();
        addingList.add(1);
        addingList.add(2);
        addingList.add(3);
        testList.addAll(addingList);
        Assertions.assertEquals(3, testList.size());
        Assertions.assertEquals(2, testList.get(1));
    }

    @Test
    void addAllWithIndex(){
        testList.add(8);
        testList.add(47);
        List<Integer> addingList = new ArrayList<>();
        addingList.add(1);
        addingList.add(2);
        addingList.add(3);
        testList.addAll(1, addingList);
        Assertions.assertEquals(5, testList.size());
        Assertions.assertEquals(1, testList.get(1));
        Assertions.assertEquals(47, testList.get(4));
    }

    @Test
    void addAllExceptionTest(){
        testList.add(8);
        testList.add(47);
        List<Integer> addingList = new ArrayList<>();
        addingList.add(1);
        addingList.add(2);
        addingList.add(3);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                testList.addAll(3, addingList));
    }

    @Test
    void removeTest(){
        List<String> secondTestList = new ArrayListByIlya<>();
        secondTestList.add("qwe");
        secondTestList.add("rty");
        Assertions.assertEquals(2, secondTestList.size());
        secondTestList.remove("rty");
        Assertions.assertEquals(1, secondTestList.size());
    }

    @Test
    void removeNotFoundTest(){
        List<String> secondTestList = new ArrayListByIlya<>();
        secondTestList.add("qwe");
        secondTestList.add("rty");
        Assertions.assertThrows(NoSuchElementException.class, () ->
                secondTestList.remove("yujioi"));
    }

    @Test
    void removeByIndexTest(){
        List<String> secondTestList = new ArrayListByIlya<>();
        secondTestList.add("qwe");
        secondTestList.add("rty");
        secondTestList.remove(0);
        Assertions.assertEquals(1, secondTestList.size());
        Assertions.assertEquals(0, secondTestList.indexOf("rty"));
    }

    @Test
    void removeByIndexExceptionTest(){
        List<String> secondTestList = new ArrayListByIlya<>();
        secondTestList.add("qwe");
        secondTestList.add("rty");
        Assertions.assertThrows(NoSuchElementException.class, () ->
                secondTestList.remove(9));
    }

    @Test
    void removeAllTest(){
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        Assertions.assertEquals(4, testList.size());
        testList.removeAll(intList);
        Assertions.assertEquals(1, testList.size());
    }

    @Test
    void removeAllExceptiontest(){
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(28);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        Assertions.assertThrows(NoSuchElementException.class, () -> testList.removeAll(intList));
    }

    @Test
    void indexOfTest(){
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        Assertions.assertEquals(2, testList.indexOf(3));
    }

    @Test
    void indexNoneTest(){
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        Assertions.assertEquals(-1, testList.indexOf(50));
    }

    @Test
    void clearTest(){
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        Assertions.assertEquals(4, testList.size());
        testList.clear();
        Assertions.assertTrue(testList.isEmpty());
    }

}
