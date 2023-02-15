package ru.ing.year2016;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class Task2016_1 {

    String path;

    public Task2016_1(String s) {
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

    public void printAnswers() throws IOException {
        List<String> listRows;

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        int lastIndex = listRows.size();
        String currentRow = "";
        int result = 0;
        String dimension = "";
        String prevDimension = "";
        int distance = 0, x = 0 ,y = 0;

        List<String> allCoords = new ArrayList<>();

        for (int i = 0; i < lastIndex; i++) {
            currentRow = listRows.get(i);
            System.out.println(" input row = " + currentRow);
            String parts[] = currentRow.split(", ");
            int[][] coordsArray;


            for (String part : parts) {
                dimension=part.substring(0,1);
                distance=Integer.valueOf(part.substring(1));
                System.out.println("dimension= " + dimension+ " distance= "+distance);

                if (dimension.equals("L")) {
                    if (prevDimension.equals("")||prevDimension.equals("U")) {x=x-distance; prevDimension="L";}
                    else if (prevDimension.equals("L")) {y=y-distance; prevDimension="D";}
                    else if (prevDimension.equals("R")) {y=y+distance; prevDimension="U";}
                    else if (prevDimension.equals("D")) {x=x+distance; prevDimension="R";}

                }

                else if (dimension.equals("R")) {

                    if (prevDimension.equals("")||prevDimension.equals("U")) {x=x+distance; prevDimension="R";}
                    else if (prevDimension.equals("L")) {y=y+distance; prevDimension="U";}
                    else if (prevDimension.equals("R")) {y=y-distance; prevDimension="D";}
                    else if (prevDimension.equals("D")) {x=x-distance; prevDimension="L";}

                }

                System.out.println("current X="+x+" y="+y + " prevDimension="+prevDimension);
                allCoords.add(x +":"+ y);
                //coordsArray.

            }

            Set<String> tempSet = new HashSet<>();

            Set<String> setOfDupl = allCoords.stream().filter(e -> !tempSet.add(e)).collect(Collectors.toSet());
            setOfDupl.stream().forEach(System.out::println);

            //allCoords.forEach(s -> {System.out.println("allCoords item="+s);});
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame();
                    JPanel panel = new JPanel(null) {
                        @Override
                        public void paint(Graphics g) {
                            super.paint(g);
                            g.drawLine(2, 2, 100, 100);
                        }
                    };
                    frame.add(panel);
                    frame.setSize(400, 400);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
        }


        System.out.println("result=" + (abs(x)+abs(y)));

        

    }

    public void printAnswers2 () throws IOException {
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

