package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        List<String> lines = InputFileReader.readLines("/input2.txt");
        int validP1 = 0;
        int validP2 = 0;
        for(String line : lines) {
            String[] pwInfo = line.split("\\s*:\\s*");
            if (isValid(pwInfo[0], pwInfo[1])) {
                validP1++;
            }
            if (isValidP2(pwInfo[0], pwInfo[1])) {
                validP2++;
            }
        }
        System.out.printf("Part 1 There are %s valid passswords.%n", validP1);
        System.out.printf("Part 2 There are %s valid passswords.%n", validP2);
    }


    private static boolean isValid(String rule, String pass) {
        String[] minMax = rule.split(" ")[0].split("-");
        int min = Integer.parseInt(minMax[0]);
        int max = Integer.parseInt(minMax[1]);
        char required = rule.charAt(rule.length()-1);
        long occurs = pass.chars().filter(ch -> ch == required).count();
        return occurs >= min && occurs <= max;
    }

    private static boolean isValidP2(String rule, String pass) {
        String[] minMax = rule.split(" ")[0].split("-");
        int pos1 = Integer.parseInt(minMax[0]) -1;
        int pos2 = Integer.parseInt(minMax[1]) -1;
        char requiredChar = rule.charAt(rule.length()-1);
        return (pass.charAt(pos1) == requiredChar && pass.charAt(pos2) != requiredChar)
                || (pass.charAt(pos1) != requiredChar && pass.charAt(pos2) == requiredChar);
    }
}
