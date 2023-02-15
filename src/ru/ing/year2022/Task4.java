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

public class Task4 {
    String path;

    public Task4(String s) {
        path = s;
    }

    public void printAnswers2() throws IOException {
        List<String> listRows;

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        int lastIndex=listRows.size();
        String currentRow="";
        int left1, left2, right1, right2;
        String interval1="";
        String interval2="";
        int result=0;

        for(int i=0; i < lastIndex; i++)
        {
            currentRow=listRows.get(i);
            System.out.println(" input row = " + currentRow);
            interval1=currentRow.substring(0,currentRow.indexOf(","));
            // System.out.println("1 part="+interval1);
            left1=Integer.valueOf(interval1.substring(0,interval1.indexOf("-")));
            //  System.out.println("left1="+left1);
            right1=Integer.valueOf(interval1.substring(interval1.indexOf("-")+1));
            //  System.out.println("right1="+right1);

            interval2=currentRow.substring(currentRow.indexOf(",")+1);
            // System.out.println("2 part="+interval2);
            left2=Integer.valueOf(interval2.substring(0,interval2.indexOf("-")));
            //  System.out.println("left2="+left2);
            right2=Integer.valueOf(interval2.substring(interval2.indexOf("-")+1));
            //  System.out.println("right2="+right2);

            if ((left1>=left2 && left1<=right2)||
                    (right1>=left2&&right1<=right2)||
                    (left2>=left1 && left2<=right1)||
                    (right2>=left1&&right2<=right1)
            )
            {
                result++;

                System.out.println("1 part="+interval1);
                System.out.println("2 part="+interval2);
                System.out.println("--------------find pairing!!!!");
            }


        }
        System.out.println("result="+result);


    }

    void printAnswers() throws IOException {
        List<String> listRows;

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        int lastIndex=listRows.size();
        String currentRow="";
        int left1, left2, right1, right2;
        String interval1="";
        String interval2="";
        int result=0;

        for(int i=0; i < lastIndex; i++)
        {
            currentRow=listRows.get(i);
            System.out.println(" input row = " + currentRow);
            interval1=currentRow.substring(0,currentRow.indexOf(","));
           // System.out.println("1 part="+interval1);
            left1=Integer.valueOf(interval1.substring(0,interval1.indexOf("-")));
          //  System.out.println("left1="+left1);
            right1=Integer.valueOf(interval1.substring(interval1.indexOf("-")+1));
          //  System.out.println("right1="+right1);

            interval2=currentRow.substring(currentRow.indexOf(",")+1);
           // System.out.println("2 part="+interval2);
            left2=Integer.valueOf(interval2.substring(0,interval2.indexOf("-")));
          //  System.out.println("left2="+left2);
            right2=Integer.valueOf(interval2.substring(interval2.indexOf("-")+1));
          //  System.out.println("right2="+right2);

            if ((left1>=left2 && right1<=right2)||
               (left2>=left1&&right2<=right1))
            {
                result++;

                System.out.println("1 part="+interval1);
                System.out.println("2 part="+interval2);
                System.out.println("--------------find pairing!!!!");
            }


        }
        System.out.println("result="+result);


    }

    public static Set<Character> toSet(String s) {
        Set<Character> ss = new HashSet<Character>(s.length());
        for (char c : s.toCharArray())
            ss.add(Character.valueOf(c));
        return ss;

    }
}
