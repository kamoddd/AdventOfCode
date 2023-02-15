package ru.ing.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task3 {
    String path;

    public Task3(String s) {
        path = s;
    }

    int getCharIndex(String symbol)
    {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int result=-1;


        result=lowerCase.indexOf(symbol)+1;
        System.out.println("lowerCase for "+symbol+" result = "+result);

        if (result == 0) {
            result = upperCase.indexOf(symbol) + 27;
            System.out.println("upperCase for " + symbol + " result = " + result);
        }

        return result;
    }

    public void printAnswers() throws IOException {
        List<String> listRows;
        AtomicReference<String> backpack1 = new AtomicReference<>();
        AtomicReference<String> backpack2 = new AtomicReference<>();

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        AtomicInteger sumWeight = new AtomicInteger(0);
        AtomicInteger currentWeight = new AtomicInteger(0);

        listRows.forEach(row -> {
            System.out.println("-----------------------------------------input row = " + row);

            backpack1.set(row.substring(0, row.length() / 2));
            backpack2.set(row.substring(row.length() / 2));
            System.out.println("backpack1=" + backpack1);
            System.out.println("backpack2=" + backpack2);


            Set<Character> problemItem = toSet(backpack1.get());
            problemItem.retainAll(toSet(backpack2.get()));

            currentWeight.set(getCharIndex(problemItem.toString().substring(1, 2)));
            System.out.println("currentWeight="+currentWeight);
            sumWeight.set(sumWeight.get()+currentWeight.get());
            System.out.println("sumWeight="+sumWeight);

        });
        System.out.println("sumWeight="+sumWeight);



    }

    String findGroupItem(String backpack1, String backpack2,String backpack3)
    {
        String groupItem="";

        Set<Character> problemItem1 = toSet(backpack1);
        problemItem1.retainAll(toSet(backpack2));
        //System.out.println("problemItem1="+problemItem1);

        problemItem1.retainAll(toSet(backpack3));
        //System.out.println("problemItem2="+problemItem1);

        groupItem=problemItem1.toString().substring(1, 2);
        //System.out.println("groupItem="+groupItem);

        return groupItem;
    }

    public void printAnswers2() throws IOException {
        List<String> listRows;
        String backpack1 = "";
        String backpack2 = "";
        String backpack3 = "";

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        int sumWeight=0;
        int currentWeight=0;
        int lastIndex=listRows.size();
        int groupIndex=0;

        for(int i=0; i < lastIndex; i++)
        {
            groupIndex++;

            if (groupIndex>3) groupIndex=1;

            if (groupIndex==1)
                backpack1=listRows.get(i);
            if (groupIndex==2)
                backpack2=listRows.get(i);
            if (groupIndex==3) {
                backpack3 = listRows.get(i);
                System.out.println(" groupItem=" + findGroupItem(backpack1,backpack2,backpack3));
                currentWeight=getCharIndex(findGroupItem(backpack1,backpack2,backpack3));
                System.out.println(" currentWeight=" + currentWeight);
                sumWeight=sumWeight+currentWeight;
                System.out.println("sumWeight="+sumWeight);
            }

            System.out.println("groupIndex="+groupIndex+" input row = " + listRows.get(i));
        }
        System.out.println("---------------sumWeight="+sumWeight);

//        listRows.forEach(row -> {
//            System.out.println("-----------------------------------------input row = " + row);
//
//            backpack1.set(row.substring(0, row.length() / 2));
//            backpack2.set(row.substring(row.length() / 2));
//            System.out.println("backpack1=" + backpack1);
//            System.out.println("backpack2=" + backpack2);
//
//
//            Set<Character> problemItem = toSet(backpack1.get());
//            problemItem.retainAll(toSet(backpack2.get()));
//
//            currentWeight.set(getCharIndex(problemItem.toString().substring(1, 2)));
//            System.out.println("currentWeight="+currentWeight);
//            sumWeight.set(sumWeight.get()+currentWeight.get());
//            System.out.println("sumWeight="+sumWeight);
//
//        });
        System.out.println("sumWeight="+sumWeight);



    }

    public static Set<Character> toSet(String s) {
        Set<Character> ss = new HashSet<Character>(s.length());
        for (char c : s.toCharArray())
            ss.add(Character.valueOf(c));
        return ss;

    }
}
