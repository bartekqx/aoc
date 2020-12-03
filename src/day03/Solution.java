package day03;

import util.InputReader;

import java.io.IOException;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.readLines("/day03/input.txt");
        final int part1 = countTrees(input, 3,1);
        final long part2 = part2(input, new int[][]{
                new int[]{1,1},
                new int[]{3,1},
                new int[]{5,1},
                new int[]{7,1},
                new int[]{1,2},
        });

        System.out.printf("part1: %d\npart2: %d", part1, part2);
    }

    private static int countTrees(List<String> lines, int slope1, int slope2) {
        int ptr = 0;
        int trees = 0;
        for (int i = 0; i < lines.size(); i += slope2) {
            final String nextLine = lines.get(i);
            if (nextLine.charAt(ptr) == '#') {
                trees++;
            }
            ptr = (ptr + slope1) % nextLine.length();
        }
        return trees;
    }

    private static long part2(List<String> lines, int[][] slopes) {
        long multiplication = 1;
        for (int i = 0; i< slopes.length; i++) {
            int trees = countTrees(lines, slopes[i][0], slopes[i][1]);
            multiplication *= trees;
        }
        return multiplication;
    }
}
