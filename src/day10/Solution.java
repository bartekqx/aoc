package day10;

import util.InputReader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<Integer> input = InputReader.readLines("/day10/input.txt")
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    private static int part1(List<Integer> input) {
        Collections.sort(input);

        int oneDiff = 1;
        int threeDiff = 1;

        for (int i = 0; i < input.size() - 1; i++) {
            final int first = input.get(i);
            final int second = input.get(i + 1);
            if (second - first == 1) {
                oneDiff++;
            } else {
                threeDiff++;
            }
        }
        return oneDiff * threeDiff;
    }

    private static long part2(List<Integer> input) {
        input.add(0);
        Collections.sort(input);
        input.add(input.get(input.size()-1) + 3);

        int[] seqs = new int[6];
        int j = 0;
        int seq = 1;
        while (j < input.size()- 1) {
            final int first = input.get(j);
            final int second = input.get(j + 1);
            if (second - first == 1) {
                seq++;
            } else {
                seqs[seq]++;
                seq = 1;
            }
            j++;
        }
        seqs[seq]++;

        return (long)Math.pow(2, seqs[3]) *
                (long) Math.pow(4, seqs[4]) *
                (long)Math.pow(7, seqs[5]);
    }

}
