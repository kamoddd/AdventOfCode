package ru.ing.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2023_1 {
    String path;
    String result;
    int final_sum=0;

    public Task2023_1(String s) {
        path = s;
    }

    public void printAnswers() throws IOException {

        List<String> listRows;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        listRows.forEach(row -> {
            //System.out.println("-----------------------------------------input row = " + row);
            row=row.replaceAll("\\D+","");
            //System.out.println("row="+row);
            //System.out.println("first="+row.substring(0,1));
            //System.out.println("second="+row.substring(row.length()-1,row.length()));
            result=row.substring(0,1)+row.substring(row.length()-1);
            //System.out.println("result="+result);
            final_sum=final_sum+Integer.valueOf(result);

        });

        System.out.println("final_sum="+final_sum);
    }
}
