package ru.ing.year2022;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Task1 {

    String path;

    public Task1(String s) {
        path = s;
    }

    public void printAnswers() throws IOException {

        List<String> listRows;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }
        final AtomicInteger[] curSum = {new AtomicInteger()};
        final AtomicInteger[] maxSum = {new AtomicInteger()};
        List<String> sumList = new ArrayList<String>();

        listRows.forEach(row -> {


            if (String.valueOf(row).equals("")) {
                if (maxSum[0].get() < curSum[0].get()) {
                    maxSum[0].set(curSum[0].get());
                }
                sumList.add(String.valueOf(curSum[0].get()));
                //System.out.println("row=" + String.valueOf(maxSum[0].get()));
                curSum[0].set(0);
            } else {

                curSum[0].set(curSum[0].get() + Integer.valueOf(row));

            }
//            System.out.println("row=" + row);
//            System.out.println("curSum=" + curSum[0]);
//            System.out.println("maxSum=" + maxSum[0]);
        });
        sumList.add(String.valueOf(curSum[0].get()));
        System.out.println("============================");
        List<String> listWithoutDuplicates = sumList.stream()
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toList());

        sumList.sort(String.CASE_INSENSITIVE_ORDER.reversed());
        System.out.println("task1-1 answer = " + sumList.get(0));
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum = sum + Integer.valueOf(sumList.get(i));
        }
        System.out.println("task1-2 answer = " + sum);
    }
}
