package ru.ing.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2023_2 {
    String path;
    int gameId = 0;
    int finalSum = 0;
    int checkIsOkFlag = 1;
    String current;

    public Task2023_2(String s) {
        path = s;
    }

    public void checkCubes(String gameDetail) {
        //System.out.println("colorCube=" + gameDetail.substring(gameDetail.indexOf(" ")).trim());
        //System.out.println("countCubes=" + gameDetail.substring(0, gameDetail.indexOf(" ")));
        String colorCube = gameDetail.substring(gameDetail.indexOf(" ")).trim();
        int countCubes = Integer.valueOf(gameDetail.substring(0, gameDetail.indexOf(" ")));
        //12 red cubes, 13 green cubes, and 14 blue cubes
        //System.out.println("colorCube=" + colorCube+" countCubes="+countCubes);
        if ((colorCube.equals("red") && countCubes > 12) ||
                (colorCube.equals("green") && countCubes > 13) ||
                (colorCube.equals("blue") && countCubes > 14))
            checkIsOkFlag = 0;
    }

    public void printAnswers() throws IOException {

        List<String> listRows;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            listRows = lines.collect(Collectors.toList());
        }

        listRows.forEach(row -> {
            System.out.println("-----------------------------------------input row = " + row);
            //row=row.replaceAll("\\D+","");
            gameId = Integer.valueOf(row.substring(5, row.indexOf(":")));
            ///System.out.println("game_id=" + gameId);
            current = row.substring(row.indexOf(":") + 1);
            //System.out.println("string=" + current);
            int pos = 0;
            String game_detail = "";
            checkIsOkFlag = 1;
            while (pos < current.length()) {

                if (current.substring(pos, pos + 1).equals(",") || current.substring(pos, pos + 1).equals(";")) {

                    //System.out.println("game_detail=" + game_detail.trim());
                    checkCubes(game_detail.trim());
                    game_detail = "";
                } else if (pos + 1 == current.length()) {
                    game_detail = game_detail + current.substring(pos, pos + 1);
                    //System.out.println("game_detail=" + game_detail.trim());
                    checkCubes(game_detail.trim());
                    game_detail = "";
                } else {
                    // System.out.println("read symbol=" + current.substring(pos, pos + 1));
                    game_detail = game_detail + current.substring(pos, pos + 1);

                }
                pos++;
            }

            if (checkIsOkFlag == 1) {
                finalSum = finalSum + gameId;
                //System.out.println("finalSum increased to " + finalSum);
            }

            //System.out.println("first="+row.substring(0,1));
            //System.out.println("second="+row.substring(row.length()-1,row.length()));
            //result=row.substring(0,1)+row.substring(row.length()-1);
            //System.out.println("result="+result);
            //final_sum=final_sum+Integer.valueOf(result);

        });

        System.out.println("finalSum=" + finalSum);
    }
}

