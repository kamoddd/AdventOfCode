package ru.ing.year2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2015_1 {

    String path;

    public Task2015_1(String s) {
        path = s;
    }

    public void printAnswers() throws IOException {
        List<String> strRow;


        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            strRow = lines.collect(Collectors.toList());
        }
        System.out.println("strRow="+strRow);
        char[] chrArray = strRow.toString().toCharArray();
        int result = 0;
        int result2 = 0;

        for (char c : chrArray) {

            System.out.println("c="+c);
            if ("(".charAt(0)==c) {
                System.out.println("+1");
                result = result + 1;
                result2++;
            } else if (")".charAt(0)==c) {
                System.out.println("-1");
                result = result - 1;
                result2++;
            }
            System.out.println("result=" + result+" index ="+result2);

            if (result==-1)
            {
                System.out.println("---------------result2=" + result2);
                return;
            }
        }
        System.out.println("result=" + result);

    }
}
