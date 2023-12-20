package com.example.ArrayListAndHashMapRealization;

import com.example.ArrayListAndHashMapRealization.realizations.ArrayListByIlya;
import com.example.ArrayListAndHashMapRealization.realizations.HashMapByIlya;

import java.util.*;

public class Main {

    public static void main(String args[]){
        Map<String, String> mapa = new HashMapByIlya<>();
        mapa.put("2", "deb");
        mapa.put("1", "bebra");
        System.out.println(mapa.get("2"));
    }
}
