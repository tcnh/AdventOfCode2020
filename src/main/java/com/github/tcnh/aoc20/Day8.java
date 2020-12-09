package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.DayEight.BootCodeFixer;
import com.github.tcnh.aoc20.DayEight.HandheldComputer;
import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.List;

public class Day8 {
    private static final List<String> instructions = InputFileReader.readLines("/input8.txt");

    public static void main(String[] args) {
        HandheldComputer.boot(instructions);
        System.out.println("Part 1: Accumulator value is " + HandheldComputer.accumulator);

        BootCodeFixer.fix(instructions);
        System.out.println("Part 2: Clean exit accumulator value is: " + HandheldComputer.accumulator);
    }
}
