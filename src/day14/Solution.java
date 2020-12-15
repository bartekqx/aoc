package day14;

import util.InputReader;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<String> input = new ArrayList<>(InputReader.readLines("/day14/input.txt"));
        final List<List<String>> grouped = groupInstructions(input);

        final long part1 = part1(grouped);

        System.out.println(part1);
        System.out.println("Siema".substring(10));
    }

    private static List<List<String>> groupInstructions(List<String> input) {
        final List<List<String>> grouped = new ArrayList<>(input.size());
        for (int i = 0; i < input.size() - 1; i++) {
            final List<String> group = new ArrayList<>();

            if (input.get(i).startsWith("mask")) {
                int j = i;
                while (j < input.size() - 1 && !input.get(j + 1).startsWith("mask")) {
                    group.add(input.get(j));
                    j++;
                }
                group.add(input.get(j));
                i = j;
            }

            grouped.add(group);
        }
        return grouped;
    }


    private static long part1(List<List<String>> grouped) {
        final Map<String, Long> memory = new HashMap<>();
        for (List<String> lines : grouped) {
            final String mask = lines.get(0).split("=")[1].trim();
            for (int i = 1; i < lines.size(); i++) {
                final String[] parts = lines.get(i).split("=");
                final String address = parts[0].substring(4, parts[0].length() - 2);
                final int value = Integer.parseInt(parts[1].trim());

                memory.put(address, getValue(mask, value));
            }
        }

        return memory.values()
                .stream()
                .reduce(0L, Long::sum);
    }

    private static long getValue(String mask, int value) {
        final String binaryValue = Integer.toBinaryString(value);
        final StringBuilder sb = new StringBuilder();
        for (int i = mask.length() - 1, j = binaryValue.length() -1; i >= 0; i--, j--) {
            if (mask.charAt(i) == 'X') {
                if (j >= 0) {
                    sb.append(binaryValue.charAt(j));
                } else {
                    sb.append("0");
                }
            } else {
                sb.append(mask.charAt(i));
            }
        }

        return Long.parseLong(sb.reverse().toString(), 2);
    }
}
