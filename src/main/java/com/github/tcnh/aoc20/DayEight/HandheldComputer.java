package com.github.tcnh.aoc20.DayEight;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandheldComputer {
    public static int accumulator = 0;

    public static int boot(List<String> instructions) {
        Set<Integer> executedInstructions = new HashSet<>();
        int pos = 0;
        do {
            executedInstructions.add(pos);
            pos = executeInstruction(pos, instructions.get(pos));
            if (pos >= instructions.size()) {
                return 0;
            }
        } while (!executedInstructions.contains(pos));
        return -1;
    }

    private static int executeInstruction(int pos, String instruction) {
        String[] instrInfo = instruction.split("\\s");
        switch (instrInfo[0]) {
            case "jmp":
                return pos + Integer.parseInt(instrInfo[1]);
            case "acc":
                accumulator += Integer.parseInt(instrInfo[1]);
            default:
                return pos + 1;
        }
    }
}
