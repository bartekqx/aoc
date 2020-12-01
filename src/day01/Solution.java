package day01;

import util.InputReader;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) throws IOException {

        final List<Integer> input = InputReader.readLines("/day01/input.txt")
                .stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        final long twoSumAnswer = twoSumAnswer(input, 2020);
        final long threeSumAnswer = threeSumAnswer(input, 2020);

        System.out.printf("part1: %d\npart2: %d", twoSumAnswer, threeSumAnswer);
    }

    private static long twoSumAnswer(List<Integer> input, int target) {
        final Set<Integer> set = new HashSet<>();
        for (int num : input) {
            int complement = target - num;
            if (set.contains(complement)) {
                return complement * num;
            }
            set.add(num);
        }
        throw new IllegalStateException();
    }

    private static long threeSumAnswer(List<Integer> input, int target) {
        Collections.sort(input);
        final int inputSize = input.size();
        for (int i = 0; i < inputSize; i++) {

            for (int j = i+1, k = inputSize - 1; j < k;) {
                int sum = input.get(i) + input.get(j) + input.get(k);
                if (sum == target) {
                    return input.get(i) * input.get(j) * input.get(k);
                }

                if (sum <= target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        throw new IllegalStateException();
    }
}
