package day13;

import util.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<String> input = new ArrayList<>(InputReader.readLines("/day13/input.txt"));

        final int myTs = Integer.parseInt(input.get(0));
        final List<Integer> busIds = Stream.of(input.get(1).split(","))
                .filter(val -> !val.equals("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(part1(busIds, myTs));
    }

    private static long part1(List<Integer> busIds, int myTs) {
        int minMinutes = Integer.MAX_VALUE;
        int fastestBusId = 0;

        for (int busId : busIds) {
            int currentBusId = busId;

            while (currentBusId  < myTs) {
                currentBusId += busId;
            }

            int mins = currentBusId - myTs;
            if (mins < minMinutes) {
                minMinutes = mins;
                fastestBusId = busId;
            }
        }

        return fastestBusId * minMinutes;
    }
}
