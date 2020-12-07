package day07;

import util.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static final Map<String, List<Bag>> groupedBags = new HashMap<>();

    public static void main(String[] args) throws IOException {
        final List<String> lines = InputReader.readLines("/day07/input.txt");

        for (String line : lines) {
            final String[] splitted = line.split("bags contain");
            final String outerBag = splitted[0].trim();

            final String[] innerBags = splitted[1].trim().split(",");
            final List<Bag> bags = new ArrayList<>();

            for (String innerBag : innerBags) {
                innerBag = innerBag.replace("bags", "").replace("bag", "").trim();
                final String[] splitQuantityAndBagName = innerBag.split(" ");
                if (!splitQuantityAndBagName[0].equalsIgnoreCase("no")) {
                    bags.add(new Bag(splitQuantityAndBagName[1] + " " + splitQuantityAndBagName[2], Integer.parseInt(splitQuantityAndBagName[0])));
                }
            }

            groupedBags.put(outerBag, bags);
        }

        final long part1 = groupedBags.keySet()
                .stream()
                .filter(Solution::isGoldBagInBag)
                .count();

        final long part2 = countBagsInShinyBag("shiny gold") - 1;

        System.out.printf("part1:%d\npart2:%d", part1, part2);
    }

    private static long countBagsInShinyBag(String bagName) {
        int ctr = 1;
        for (Bag innerBag : groupedBags.get(bagName)) {
            ctr += (innerBag.getQ() * countBagsInShinyBag(innerBag.getName()));
        }
        return ctr;
    }

    private static boolean isGoldBagInBag(String bag) {
        for (Bag innerBag : groupedBags.get(bag)) {
            if (innerBag.getName().equalsIgnoreCase("shiny gold")) {
                return true;
            }
        }

        for (Bag innerBag : groupedBags.get(bag)) {
            if (isGoldBagInBag(innerBag.getName())) {
                return true;
            }
        }

        return false;
    }

    static class Bag {
        private final String name;
        private final int q;

        public Bag(String name, int q) {
            this.name = name;
            this.q = q;
        }

        public String getName() {
            return name;
        }

        public int getQ() {
            return q;
        }
    }
}