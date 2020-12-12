package day12;

import util.InputReader;

import java.io.IOException;
import java.util.List;

public class Solution {


    private static String [] directions = new String[] {
            "N",
            "E",
            "S",
            "W"
    };

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.readLines("/day12/input.txt");

        final long part1 = part1(input);
        final long part2 = part2(input);
        System.out.println(part1);
        System.out.println(part2);
    }

    private static long part2(List<String> input) {
        int x = 0, y = 0;
        int wX = 10, wY = 1;


        for (String line : input) {
            final String command = line.substring(0,1);
            final int value = Integer.parseInt(line.substring(1));

            int amt = value / 90;
            switch (command) {
                case "R" :
                    for (int i = 0; i < amt; i++) {
                        int tmp = wY;
                        wY = wX * -1;
                        wX = tmp;
                    }
                    break;
                case "L" :
                    for (int i = 0; i < amt; i++) {
                        int tmp = wX;
                        wX = wY * -1;
                        wY = tmp;
                    }
                    break;
                case "N" :
                    wY+= value;
                    break;
                case "E" :
                    wX += value;
                    break;
                case "S" :
                    wY -= value;
                    break;
                case "W" :
                    wX -= value;
                    break;
                case "F" :
                    x += value * wX;
                    y += value * wY;
                    break;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    private static long part1(List<String> input) {
        int direction = 1;
        int x = 0, y = 0;

        for (String line : input) {
            final String command = line.substring(0,1);
            final int value = Integer.parseInt(line.substring(1));

            switch (command) {
                case "R" :
                case "L" :
                    direction = findDirection(command, direction, value);
                    break;
                case "N" :
                    y+=value;
                    break;
                case "E" :
                    x += value;
                    break;
                case "S" :
                    y -= value;
                    break;
                case "W" :
                    x -= value;
                    break;
                case "F" :
                    if (direction == 0) {
                        y+=value;
                    } else if (direction == 1) {
                        x+=value;
                    } else if (direction == 2) {
                        y-=value;
                    } else if (direction == 3) {
                        x-=value;
                    }
                    break;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    private static int findDirection(String command, int direction, Integer value) {
        final int idxToMove = value / 90;
        switch (command) {
            case "R" :
                return (direction + idxToMove) % directions.length;
            case "L" :
                int i = 0;
                while (i < idxToMove) {
                    if (direction == 0) {
                        i++;
                        direction = 3;
                    } else {
                        direction -= 1;
                        i++;
                    }
                }
                return direction;
        }
        throw new IllegalStateException();
    }
}
