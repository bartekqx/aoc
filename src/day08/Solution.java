package day08;

import util.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<String> lines = InputReader.readLines("/day08/input.txt");

        final long part1 = part1(lines).getAccumulator();
        final long part2 = part2(lines);

        System.out.printf("part1:%d\npart2:%d", part1, part2);
    }

    private static long part2(List<String> lines) {
        final List<Integer> jmps = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            final String line = lines.get(i);
            if (line.contains("jmp")) {
                jmps.add(i);
            }
        }

        int jmpsPtr = 0;
        for (int i = 0; i < jmps.size(); i++) {
            final Status status = part1(lines);

            if (status.isSucceed()) {
                return status.getAccumulator();
            }

            if (jmpsPtr > 0) {
                final int idxRevert = jmps.get(jmpsPtr - 1);
                lines.set(idxRevert, lines.get(idxRevert).replace("nop", "jmp"));
            }

            final int idxToChange = jmps.get(jmpsPtr);
            lines.set(idxToChange, lines.get(idxToChange).replace("jmp", "nop"));
            jmpsPtr++;
        }

        throw new IllegalStateException();
    }

    private static Status part1(List<String> lines) {
        final Set<Integer> operations = new HashSet<>();

        long accumulator = 0;
        for (int i = 0; i < lines.size(); i++) {
            final String operation = lines.get(i);
            if (operations.contains(i)) {
                return new Status(false, accumulator);
            }
            operations.add(i);
            final String[] splitted = operation.split(" ");
            final String op = splitted[0];
            final String sign = splitted[1].substring(0, 1);
            final int value = Integer.parseInt(splitted[1].substring(1));

            switch (op) {
                case "acc" :
                    if (sign.equals("+")) {
                        accumulator += value;
                    } else {
                        accumulator -= value;
                    }
                    break;

                case "jmp" :
                    if (sign.equals("+")) {
                        i += (value -1);
                    } else {
                        i -= (value +1);
                    }
                    break;

                default:
            }
        }
        return new Status(true, accumulator);
    }

    private static class Status {
        private final boolean isSucceed;
        private final long accumulator;

        public Status(boolean isSucceed, long accumulator) {
            this.isSucceed = isSucceed;
            this.accumulator = accumulator;
        }

        public boolean isSucceed() {
            return isSucceed;
        }

        public long getAccumulator() {
            return accumulator;
        }
    }
}
