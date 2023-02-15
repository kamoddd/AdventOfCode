package ru.ing.year2022;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Task2 {

    String path;

    public Task2(String s) {
        path = s;
    }


    int calcScore2(String player1, String player2) {
        int result = 0;
        int win = 6, lost = 0, draw = 3;
        int rock = 1, paper = 2, scissors = 3;

        /*
        the second column says how the round needs to end:
        X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"

        (1 for Rock(A-X), 2 for Paper(B-Y), and 3 for Scissors(C-Z))
         */
        if (String.valueOf(player2).equals("X")) {
            result=lost;
            if (String.valueOf(player1).equals("A")) {
                return result + scissors;
            } else if (String.valueOf(player1).equals("B")) {
                return result + rock;
            } else if (String.valueOf(player1).equals("C")) {
                return result + paper;
            }
        } else if (String.valueOf(player2).equals("Y")) {
            result=draw;
            if (String.valueOf(player1).equals("A")) {
                return result + rock;
            } else if (String.valueOf(player1).equals("B")) {
                return result + paper;
            } else if (String.valueOf(player1).equals("C")) {
                return result + scissors;
            }
        } else if (String.valueOf(player2).equals("Z")) {
            result=win;
            if (String.valueOf(player1).equals("A")) {
                return result + paper;
            } else if (String.valueOf(player1).equals("B")) {
                return result + scissors;
            } else if (String.valueOf(player1).equals("C")) {
                return result + rock;
            }
        }

        return result;

    }

    int calcScore(String player1, String player2) {
        int result = 0;
        int win = 6, lost = 0, draw = 3;
        /*
        shape you selected (1 for Rock(A-X), 2 for Paper(B-Y), and 3 for Scissors(C-Z))
        plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won)
         */

        if (String.valueOf(player2).equals("X")) {
            result = 1;
            if (String.valueOf(player1).equals("A")) {
                return result + draw;
            } else if (String.valueOf(player1).equals("B")) {
                return result + lost;
            } else if (String.valueOf(player1).equals("C")) {
                return result + win;
            }

        } else if (String.valueOf(player2).equals("Y")) {
            result = 2;
            if (String.valueOf(player1).equals("B")) {
                return result + draw;
            } else if (String.valueOf(player1).equals("C")) {
                return result + lost;
            } else if (String.valueOf(player1).equals("A")) {
                return result + win;
            }

        } else if (String.valueOf(player2).equals("Z")) {
            result = 3;
            if (String.valueOf(player1).equals("C")) {
                return result + draw;
            } else if (String.valueOf(player1).equals("A")) {
                return result + lost;
            } else if (String.valueOf(player1).equals("B")) {
                return result + win;
            }
        }

        return result;
    }

    public void printAnswers() throws IOException {
        AtomicInteger result = new AtomicInteger();
        AtomicInteger result2 = new AtomicInteger();
        //System.out.println("calcScore2="+calcScore2("C","Z"));
        List<String> listRows;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }


        listRows.forEach(row -> {
            System.out.println("-----------------------------------------input row = " + row);
            String player1 = row.substring(0, 1);
            String player2 = row.substring(2, 3);
            System.out.println("input player1 = " + player1);
            System.out.println("input player2 = " + player2);
            result.set(result.get() + calcScore(player1, player2));
            result2.set(result2.get() + calcScore2(player1, player2));

            System.out.println("calcScore=" + result.get());
            System.out.println("calcScore2=" + result2.get());

        });
        System.out.println("final result1=" + result.get());
        System.out.println("final result2=" + result2.get());
    }
}
