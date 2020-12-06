package day05;

import util.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<Integer> ids = InputReader.readLines("/day05/input.txt")
                .stream()
                .map(s -> {
                    final int idx = findBorderIdx(s);
                    final int r = get(s.substring(0, idx), 'F', 'B', 0, 127);
                    final int c = get(s.substring(idx), 'L', 'R', 0, 7);
                    return r * 8 + c;
                })
                .sorted()
                .collect(Collectors.toList());

        final long part1 = ids.get(ids.size()-1);
        final long part2 = part2(ids);

        System.out.printf("part1:%d\npart2:%d", part1, part2);
    }

    private static long part2(List<Integer> ids) {
        for (int i = 0; i < ids.size() - 1; i++) {
            if ((ids.get(i) + 1) != ids.get(i + 1)) {
                return ids.get(i) + 1;
            }
        }
        throw new IllegalStateException();
    }

    private static int findBorderIdx(String s) {
        for (int i = 0; i < s.length() ; i++) {
            if (s.charAt(i) == 'R' || s.charAt(i) == 'L') {
                return i;
            }
        }
        throw new IllegalStateException();
    }

    private static int get(String in, char f, char s, int up, int down) {
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == f) {
                down-= (1 + (down - up)) / 2;
            } else if (in.charAt(i) == s) {
                up+= (1 + (down - up)) / 2;
            }
        }

        return in.charAt(in.length() - 1) == f
                ? up
                : down;
    }
}
