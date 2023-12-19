package com.example.ArrayListAndHashMapRealization;

import com.example.ArrayListAndHashMapRealization.realizations.ArrayListByIlya;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]){
        List<String> str = new ArrayListByIlya<>();
        str.add("unvui");
        str.add("eufhiu");
        str.forEach(s -> System.out.println(s));
        System.out.println("List size is: " + str.size());
        System.out.println("List is empty: " + str.isEmpty());
        str.clear();
        str.forEach(s -> System.out.println(s));
        System.out.println("List size is: " + str.size());
        System.out.println("List is empty: " + str.isEmpty());
    }
}
