package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        List<Integer> lines = InputFileReader.readLinesToIntegers("/input1.txt");
        System.out.println("Day 1 part 1: " + part1(lines));
        System.out.println("Day 1 part 2: " + part2(lines));
    }

    private static int part1(List<Integer> input) {
        for (int entry : input) {
            int wanted = 2020 - entry;
            if (input.contains(wanted)) {
                return wanted * entry;
            }
        }
        throw new RuntimeException("No answer found!");
    }

    private static int part2(List<Integer> input) {
        for (int first : input) {
            for (int second : input) {
                if (first + second < 2020) {
                    int wanted = 2020 - first - second;
                    if (input.contains(wanted)) {
                        return wanted * first * second;
                    }
                }
            }
        }
        throw new RuntimeException("No answer found!");
    }
}
