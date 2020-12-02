package day02;

import util.InputReader;

import java.io.IOException;
import java.util.List;

public class Solution {

    private static final String OLD = "OLD";
    private static final String CURRENT = "CURRENT";

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.readLines("/day02/input.txt");

        final long part1ValidPasswords = input.stream()
                .map(line -> createPassword(line, OLD))
                .filter(Password::isValid)
                .count();

        final long part2ValidPasswords = input.stream()
                .map(line -> createPassword(line, CURRENT))
                .filter(Password::isValid)
                .count();

        System.out.printf("part1:%d\npart2:%d", part1ValidPasswords, part2ValidPasswords);
    }

    private static Password createPassword(String line, String strategy) {
        final String[] policyAndPassword = line.split(":");
        final String[] minMaxAndLetter = policyAndPassword[0].split(" ");
        final String[] minMax = minMaxAndLetter[0].split("-");

        final PasswordPolicy passwordPolicy = createPolicy(strategy, minMaxAndLetter[1].charAt(0), Integer.parseInt(minMax[0]), Integer.parseInt(minMax[1]));
        return new Password(policyAndPassword[1], passwordPolicy);
    }

    private static PasswordPolicy createPolicy(String strategy, char letter, int min, int max) {
        return OLD.equals(strategy)
                ? new OldPasswordPolicy(letter, min, max)
                : new CurrentPasswordPolicy(letter, min, max);
    }
}


class Password {

    private final String password;
    private final PasswordPolicy passwordPolicy;

    public Password(String password, PasswordPolicy passwordPolicy) {
        this.password = password;
        this.passwordPolicy = passwordPolicy;
    }

    public boolean isValid() {
        return passwordPolicy.isValid(password);
    }
}