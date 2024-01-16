package ru.ing.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2023_4 {
    String path;
    int final_sum=0;

    public Task2023_4(String s) {
        path = s;
    }

    public void printAnswers() throws IOException {

        List<String> listRows;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        listRows.forEach(row -> {
            System.out.println("-----------------------------------------input row = " + row);
            //System.out.println("row="+row);
            String firstPart="";
            String secondPart="";
            int rowWinCount=0;
            int firstWinFlag=0;
            String currentDigit="";
            firstPart=row.substring(row.indexOf(":")+1,row.indexOf("|")).replaceAll("\\D+","*");
            secondPart=row.substring(row.indexOf("|")+2).replaceAll("\\D+","*")+"*";
            //System.out.println("first="+firstPart);
            //System.out.println("second="+secondPart);
            int pos=0;
            while (pos<secondPart.length())
            {

                if (secondPart.substring(pos,pos+1).equals("*")||pos==secondPart.length()-1)
                {
                    //System.out.println("currentDigit="+currentDigit);
                    if (firstPart.indexOf("*"+currentDigit+"*")>=0)
                    {
                        if (firstWinFlag==0) {
                            firstWinFlag=1;
                            rowWinCount=firstWinFlag;
                        } else
                        {
                            rowWinCount=rowWinCount*2;
                        }
                    }
                    //System.out.println("rowWinCount="+rowWinCount);
                    currentDigit="";
                } else
                {
                    currentDigit=currentDigit+secondPart.substring(pos,pos+1);
                }
            pos++;
            }
            final_sum=final_sum+rowWinCount;
            System.out.println("rowWinCount="+rowWinCount);

        });

        System.out.println("final_sum="+final_sum);
    }
}
