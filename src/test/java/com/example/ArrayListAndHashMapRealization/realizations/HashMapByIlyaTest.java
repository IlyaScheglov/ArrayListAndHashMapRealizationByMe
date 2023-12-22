package com.example.ArrayListAndHashMapRealization.realizations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapByIlyaTest {

    private Map<String, String> testMap;

    @BeforeEach
    void setUp(){
        testMap = new HashMapByIlya<>();
    }

    @Test
    void putTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        Assertions.assertEquals(2, testMap.size());
    }

    @Test
    void putTwoEqualsElementsTest(){
        testMap.put("1", "wsw");
        testMap.put("1", "ec");
        Assertions.assertEquals(1, testMap.size());
        Assertions.assertEquals("ec", testMap.get("1"));
    }

    @Test
    void sizeTest(){
        testMap.put("1", "ducih");
        testMap.put("2", "uviiv");
        testMap.put("3", "ucdbcu");
        testMap.put("3", "lvf");
        Assertions.assertEquals(3, testMap.size());
    }

    @Test
    void isEmptyTrueTest(){
        testMap.put("1", "ducih");
        testMap.remove("1");
        Assertions.assertTrue(testMap.isEmpty());
    }

    @Test
    void isEmptyFalseTest(){
        testMap.put("1", "ducih");
        testMap.put("1", "hdj");
        Assertions.assertFalse(testMap.isEmpty());
    }

    @Test
    void getTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        Assertions.assertEquals("iki", testMap.get("1"));
    }

    @Test
    void getNullTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        Assertions.assertEquals(null, testMap.get("99"));
    }

    @Test
    void containsKeyTrueTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.put("3", "gr");
        testMap.put("4", "ds");
        testMap.put("5", "ikvfvi");
        Assertions.assertTrue(testMap.containsKey("5"));
    }

    @Test
    void containsKeyFalseTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.put("3", "gr");
        testMap.put("4", "ds");
        Assertions.assertFalse(testMap.containsKey("5"));
    }

    @Test
    void clearTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.put("3", "gr");
        testMap.put("4", "ds");
        testMap.clear();
        Assertions.assertEquals(0, testMap.size());
    }

    @Test
    void valuesTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.put("3", "gr");
        LinkedList<String> valList = (LinkedList<String>) testMap.values();
        String[] array = {"iki", "cd", "gr"};
        AtomicInteger indexCheck = new AtomicInteger();
        valList.forEach(vl -> {
            Assertions.assertEquals(array[indexCheck.get()], vl);
            indexCheck.getAndIncrement();
        });
    }

    @Test
    void putAllTest(){
        Map<String, String> mapa = new HashMap<>();
        mapa.put("1", "ghjk");
        mapa.put("2", "hcj");
        testMap.putAll(mapa);
        Assertions.assertEquals("ghjk", testMap.get("1"));
        Assertions.assertEquals("hcj", testMap.get("2"));
    }

    @Test
    void putIfAbsentReallyAbsentTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.put("3", "gr");
        testMap.putIfAbsent("58", "fgh");
        Assertions.assertEquals(4, testMap.size());
        Assertions.assertEquals("fgh", testMap.get("58"));
    }

    @Test
    void putIfAbsentPresentTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.put("3", "gr");
        testMap.putIfAbsent("3", "fgh");
        Assertions.assertEquals(3, testMap.size());
        Assertions.assertEquals("gr", testMap.get("3"));
    }

    @Test
    void removeTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.remove("2");
        Assertions.assertEquals(1, testMap.size());
        Assertions.assertEquals(null, testMap.get("2"));
    }

    @Test
    void removeNoElementTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.remove("3");
        Assertions.assertEquals(2, testMap.size());
        Assertions.assertEquals("cd", testMap.get("2"));
    }

    @Test
    void removeByTwoArgsTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.remove("2", "cd");
        Assertions.assertEquals(1, testMap.size());
        Assertions.assertEquals(null, testMap.get("2"));
    }

    @Test
    void removeByTwoArgsNoElementTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.remove("2", "fghj");
        Assertions.assertEquals(2, testMap.size());
        Assertions.assertEquals("cd", testMap.get("2"));
    }

    @Test
    void keySetTest(){
        testMap.put("1", "iki");
        testMap.put("2", "cd");
        testMap.put("3", "gr");
        Set<String> kSet = testMap.keySet();
        Assertions.assertEquals(3, kSet.size());
        Assertions.assertTrue(kSet.contains("1"));
        Assertions.assertTrue(kSet.contains("2"));
        Assertions.assertTrue(kSet.contains("3"));
        Assertions.assertFalse(kSet.contains("4"));
    }

}
