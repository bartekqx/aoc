package day04;

import util.InputReader;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.readLines("/day04/input.txt");
        final Set<String> requiredFields = new HashSet<>(Arrays.asList(
           "iyr",
           "hcl",
           "hgt",
           "eyr",
           "byr",
           "pid",
           "ecl"
        ));

        final long part1 = validatePassports(input, requiredFields, new PassportRequiredFieldsValidator());
        final long part2 = validatePassports(input, requiredFields, new PassportFieldDataValidator());

        System.out.printf("part1: %d\npart2: %d", part1, part2);
    }

    private static long validatePassports(List<String> input, Set<String> requiredFields, PassportValidator passportValidator) {
        final Map<String, String> passportFields =  new HashMap<>();
        long validPassports  = 0;
        for (String line : input) {
            if (line.equals("")) {
                if (passportValidator.isValid(passportFields, requiredFields)) {
                    validPassports++;
                }
                passportFields.clear();
                continue;
            }
            final String[] fields = line.split(" ");
            for (String field : fields) {
                final String[] f = field.split(":");
                passportFields.put(f[0], f[1]);
            }
        }
        return validPassports;
    }

    interface PassportValidator {
        boolean isValid(Map<String,String> passportFields, Set<String> requiredFields);
    }

    static class PassportRequiredFieldsValidator implements PassportValidator {

        @Override
        public boolean isValid(Map<String, String> passportFields, Set<String> requiredFields) {
            return requiredFields.stream()
                    .allMatch(passportFields::containsKey);
        }
    }

    static class  PassportFieldDataValidator implements PassportValidator {

        private final Pattern heightPattern = Pattern.compile("(\\d+)(in|cm)");
        private final Pattern hairPattern = Pattern.compile("(#)(([0-9]|[a-f]){6})");
        private final Pattern eyeColorPattern = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth");
        private final Pattern pidPattern = Pattern.compile("[0-9]{9}");

        @Override
        public boolean isValid(Map<String, String> passportFields, Set<String> requiredFields) {
            final boolean reqFieldsPresent = requiredFields.stream()
                    .allMatch(passportFields::containsKey);

            if (!reqFieldsPresent) {
                return false;
            }

            final int bYear = Integer.parseInt(passportFields.get("byr"));
            final int iYear = Integer.parseInt(passportFields.get("iyr"));
            final int eYear = Integer.parseInt(passportFields.get("eyr"));
            final String height = passportFields.get("hgt");
            final String hairColor = passportFields.get("hcl");
            final String eyeColor = passportFields.get("ecl");
            final String pid = passportFields.get("pid");

            return bYear >= 1920 && bYear <= 2002 &&
                    iYear >= 2010 && iYear <= 2020 &&
                    eYear >= 2020 && eYear <= 2030 &&
                    isHeightValid(height) &&
                    hairPattern.matcher(hairColor).matches() &&
                    eyeColorPattern.matcher(eyeColor).matches() &&
                    pidPattern.matcher(pid).matches();
        }

        private boolean isHeightValid(String height) {
            final Matcher matcher = heightPattern.matcher(height);

            if (matcher.matches()) {
                final int val = Integer.parseInt(matcher.group(1));
                final String unit = matcher.group(2);

                if (unit.equals("cm") && val >= 150 && val <= 193) {
                    return true;
                } else {
                    return unit.equals("in") && val >= 59 && val <= 76;
                }
            }
            return false;
        }
    }
}
