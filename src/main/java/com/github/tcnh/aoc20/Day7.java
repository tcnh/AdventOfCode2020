package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Day7 {
    static final String SHINY_GOLD = "shiny gold";
    static List<String> bagSpecs = InputFileReader.readLines("/input7.txt");
    static Map<String, String> bags = bagSpecs.stream().map(spec -> spec.split("\\s*bags\\scontain\\s*"))
            .collect(toMap(info -> info[0], info -> info[1], (a, b) -> b));

    public static void main(String[] args) {
        System.out.println("Part 1: " + bags.keySet().stream().filter(Day7::canHoldShinyGold).count());
        System.out.println("Part 2: " + countBagsInside(SHINY_GOLD, 1));
    }

    private static boolean canHoldShinyGold(String color) {
        boolean result = false;
        color = color.trim();
        if (!color.contains("no other")) {
            if (bags.get(color).contains(SHINY_GOLD)) {
                result = true;
            } else {
                for (String content : bags.get(color).split("\\s*,\\s*")) {
                    if (!result) {
                        String cleanColor = content.replaceAll("\\d+\\s*", "")
                                .replaceAll("bags?\\.?", "").trim();
                        result = canHoldShinyGold(cleanColor);
                    }
                }
            }
        }
        return result;
    }

    private static int countBagsInside(String color, int number) {
        int sumOfBags = 0;
        for (String content : bags.get(color).split("\\s*,\\s*")) {
            if (!content.contains("no other")) {
                int newNumber = Integer.parseInt(content.trim().substring(0, 1));
                String cleanedColor = content.replaceAll("\\d+\\s*", "")
                        .replaceAll("bags?\\.?", "").trim();
                sumOfBags += number * newNumber;
                sumOfBags += number * countBagsInside(cleanedColor, newNumber);
            }
        }
        return sumOfBags;
    }
}
