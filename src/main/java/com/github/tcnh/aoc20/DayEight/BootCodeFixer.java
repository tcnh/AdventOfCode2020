package com.github.tcnh.aoc20.DayEight;

import java.util.List;

import static com.github.tcnh.aoc20.DayEight.HandheldComputer.accumulator;
import static com.github.tcnh.aoc20.DayEight.HandheldComputer.boot;

public class BootCodeFixer {
    public static void fix(List<String> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            if (!instructions.get(i).startsWith("acc")) {
                int result = -1;
                accumulator = 0;
                String[] instrInfo = instructions.get(i).split("\\s");
                switch (instrInfo[0]) {
                    case "jmp":
                        instructions.set(i, "nop " + instrInfo[1]);
                        result = boot(instructions);
                        instructions.set(i, "jmp " + instrInfo[1]);
                        break;
                    case "nop":
                        instructions.set(i, "jmp " + instrInfo[1]);
                        result = boot(instructions);
                        instructions.set(i, "nop " + instrInfo[1]);
                        break;
                }
                if (result == 0) {
                    return;
                }
            }
        }
    }
}
