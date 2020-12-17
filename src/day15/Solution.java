package day15;

import util.InputReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<Integer> input = Stream.of(InputReader.readLines("/day15/input.txt").get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        final int part1 = solve(2020, input);
        final int part2 = solve(30000000, input);

        System.out.println(part1);
        System.out.println(part2);
    }

    private static int solve(int range, List<Integer> input) {
        final Map<Integer, Pair> map = new HashMap<>();

        int turn = 1;
        int lastSpoken = 0;
        for (int i = 0; i < input.size(); i++) {
            if (i == input.size() - 1) {
                lastSpoken = input.get(i);
            } else {
                map.put(input.get(i), new Pair(turn++, 0));
            }
        }

        for (int i = turn; i < range; i++) {
            Pair p = map.get(lastSpoken);

            if (p != null) {
                p.setOldest(p.getLatest());
                p.setLatest(i);

                map.put(lastSpoken, p);
                p = map.get(lastSpoken);
                lastSpoken = p.getLatest() - p.getOldest();
            } else {
                map.put(lastSpoken, new Pair(i, 0));
                lastSpoken = 0;
            }
        }
        return lastSpoken;
    }

    private static class Pair {
        private int latest;
        private int oldest;

        public Pair(int latest, int oldest) {
            this.latest = latest;
            this.oldest = oldest;
        }

        public int getLatest() {
            return latest;
        }

        public void setLatest(int latest) {
            this.latest = latest;
        }

        public int getOldest() {
            return oldest;
        }

        public void setOldest(int oldest) {
            this.oldest = oldest;
        }
    }
}
