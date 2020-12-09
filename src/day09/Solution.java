package day09;

import util.InputReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<Long> values = InputReader.readLines("/day09/input2.txt")
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        final long part1 = part1(values, 5);
        final long part2 = part2(values, 5);

        System.out.printf("part1:%d\npart2:%d", part1, part2);
    }

    private static long part1(List<Long> values, int preamble) {
        return findInvalidNumber(values, preamble);
    }

    private static long part2(List<Long> values, int preamble) {
        final long invalidNumber = findInvalidNumber(values, preamble);

        long sum = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int i = 0,j = 0; i < values.size(); i++) {
            final long val = values.get(i);
            min = Math.min(val, min);
            max = Math.max(val, max);

            sum += val;

            if (sum == invalidNumber) {
                return min + max;
            } else if (sum > invalidNumber) {
                j++;
                i = j;
                sum = 0;
                min = Long.MAX_VALUE;
                max = Long.MIN_VALUE;
            }

        }

        throw new IllegalStateException();
    }

    private static long findInvalidNumber(List<Long> values, int preamble) {
        for (int i = preamble, j = 0; i < values.size(); i++, j++) {
            final long val = values.get(i);
            if (!isTwoSum(values, j, i, val)) {
                return val;
            }
        }
        throw new IllegalStateException();
    }

    private static boolean isTwoSum(List<Long> values, int lowIdx, int highIdx, long target) {
        final Set<Long> set = new HashSet<>();

        for (int i = lowIdx; i < highIdx; i++) {
            final long complement = target - values.get(i);
            if (set.contains(complement)) {
                return true;
            }
            set.add(values.get(i));
        }

        return false;
    }
}
