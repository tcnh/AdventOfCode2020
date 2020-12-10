package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 {
    private static final ArrayList<Integer> MULTIPLIERS = new ArrayList<>(List.of(0, 1, 1, 2, 4, 7, 13));

    public static void main(String[] args) {
        int oneJoltSteps = 0;
        int threeJoltSteps = 0;
        int currentJoltage = 0;

        List<Integer> adapters = InputFileReader.readLinesToIntegers("/input10.txt");
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3); //Last one is always +3 from highest

        for (int adapter : adapters) {
            if (adapter - currentJoltage == 1) {
                oneJoltSteps++;
            } else if (adapter - currentJoltage == 3) {
                threeJoltSteps++;
            }
            currentJoltage = adapter;
        }
        System.out.printf("Part 1: %s x %s is %s\r\n", oneJoltSteps, threeJoltSteps, oneJoltSteps * threeJoltSteps);

        oneJoltSteps = 1;
        currentJoltage = 0;
        long routes = 1L;
        for (int adapter : adapters) {
            if (adapter - currentJoltage == 1) {
                oneJoltSteps++;
            } else {
                routes *= MULTIPLIERS.get(oneJoltSteps);
                oneJoltSteps = 1;
            }
            currentJoltage = adapter;
        }
        System.out.println("Part 2: There are " + routes + " possible routes");
    }
}
