package day06;

import util.InputReader;

import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.readLines("/day06/input.txt");


        final long part1 = part1(input);
        final long part2 = part2(input);

        System.out.printf("part1:%d\npart2:%d", part1, part2);
    }

    private static long part1(List<String> input) {
        final Set<Character> set = new HashSet<>();

        long sum = 0;
        for (String line : input) {
            if (line.isEmpty()) {
                sum += set.size();
                set.clear();
            }

            for (int i = 0; i < line.length(); i++) {
                set.add(line.charAt(i));
            }
        }

        return sum;
    }

    private static long part2(List<String> input) {
        final Map<Character, Long> map = new HashMap<>();

        long sum = 0;
        long groupSize = 0;

        for (String line : input) {
            if (line.isEmpty()) {
                sum += countYesAnswers(map, groupSize);
                map.clear();
                groupSize = 0;
                continue;
            }

            for (int i = 0; i < line.length(); i++) {
                map.merge(line.charAt(i), 1L, Long::sum);
            }
            groupSize += 1;
        }

        return sum;
    }

    private static long countYesAnswers(Map<Character, Long> map, long groupSize) {
        return map.values()
                .stream()
                .filter(v -> v == groupSize)
                .count();
    }
}
