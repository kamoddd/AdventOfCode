package ru.ing.year2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2015_2 {

    String path;

    public Task2015_2(String s) {
        path = s;
    }

    public int getMinimalValue(int l, int w, int h) {
        int[] arr = new int[]{l, w, h};
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        System.out.println("Smallest element: " + min);
        return min;
    }

    public void printAnswers2() throws IOException {
        List<String> listRows;

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        int lastIndex = listRows.size();
        String currentRow = "";
        int result = 0;
        int curSum = 0;
        for (int i = 0; i < lastIndex; i++) {
            currentRow = listRows.get(i);
            System.out.println(" input row = " + currentRow);
            String parts[] = currentRow.split("x");
            int l = 0, w = 0, h = 0;
            for (String part : parts) {
                if (l == 0) l = Integer.valueOf(part);
                else if (w == 0) w = Integer.valueOf(part);
                else if (h == 0) h = Integer.valueOf(part);
            }
            System.out.println("result " + l + w + h);

            curSum = l*w*h + getMinimalValue(2*(l+w), 2*(w+h), 2*(l+h));
            System.out.println("curSum=" + curSum);
            result = result + curSum;
        }
        System.out.println("result=" + result);

    }

    public void printAnswers() throws IOException {
        List<String> listRows;

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        int lastIndex = listRows.size();
        String currentRow = "";
        int result = 0;
        int curSum = 0;
        for (int i = 0; i < lastIndex; i++) {
            currentRow = listRows.get(i);
            System.out.println(" input row = " + currentRow);
            String parts[] = currentRow.split("x");
            int l = 0, w = 0, h = 0;
            for (String part : parts) {
                if (l == 0) l = Integer.valueOf(part);
                else if (w == 0) w = Integer.valueOf(part);
                else if (h == 0) h = Integer.valueOf(part);
            }
            System.out.println("result " + l + w + h);
            //2*l*w + 2*w*h + 2*h*l
            curSum = 2 * l * w + 2 * w * h + 2 * h * l + getMinimalValue(l * w, w * h, h * l);
            System.out.println("curSum=" + curSum);
            result = result + curSum;
        }
        System.out.println("result=" + result);

    }

}

