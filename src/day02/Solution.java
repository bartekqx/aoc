package day02;

import util.InputReader;

import java.io.IOException;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.readLines("/day02/input.txt");

        final long part1 = input.stream()
                .map(line -> {
                    final String[] policyAndPassword = line.split(":");
                    final String[] minMaxAndLetter = policyAndPassword[0].split(" ");
                    final String[] minMax = minMaxAndLetter[0].split("-");
                    return part1(policyAndPassword[1], minMaxAndLetter[1].charAt(0), Integer.parseInt(minMax[0]), Integer.parseInt(minMax[1]));
                })
                .filter(b -> b)
                .count();

        final long part2 = input.stream()
                .map(line -> {
                    final String[] policyAndPassword = line.split(":");
                    final String[] minMaxAndLetter = policyAndPassword[0].split(" ");
                    final String[] minMax = minMaxAndLetter[0].split("-");
                    return part2(policyAndPassword[1], minMaxAndLetter[1].charAt(0), Integer.parseInt(minMax[0]), Integer.parseInt(minMax[1]));
                })
                .filter(b -> b)
                .count();

        System.out.println("part1: " + part1);
        System.out.println("part2: " + part2);
    }

    private static boolean part1(String password, char letter, int min, int max) {
        int letterCnt = 0;
        for (int i = 0; i < password.length(); i++) {
            if (letterCnt > max) {
                return false;
            }
            if (password.charAt(i) == letter) {
                letterCnt++;
            }
        }

        return letterCnt >= min && letterCnt <= max;
    }

    private static boolean part2(String password, char letter, int p1, int p2) {
        return (password.charAt(p1) == letter && password.charAt(p2) != letter) ||
                (password.charAt(p1) != letter && (password.charAt(p2) == letter));
    }
}