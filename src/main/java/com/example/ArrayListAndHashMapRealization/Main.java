package com.example.ArrayListAndHashMapRealization;

import com.example.ArrayListAndHashMapRealization.obj.KeyAndValueObject;
import com.example.ArrayListAndHashMapRealization.realizations.ArrayListByIlya;
import com.example.ArrayListAndHashMapRealization.realizations.HashMapByIlya;

import java.util.*;

public class Main {

    public static void main(String args[]){
        Map<String, String> mapa = new HashMapByIlya<>();
        mapa.putIfAbsent("1", "veev");
        System.out.println(mapa.remove("1", "veev"));
    }
}
