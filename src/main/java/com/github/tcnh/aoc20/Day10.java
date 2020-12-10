package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {
        int oneJoltSteps = 0;
        int threeJoltSteps = 0;
        int currentJoltage = 0;

        List<Integer> adapters = InputFileReader.readLinesToIntegers("/input10.txt");
        Collections.sort(adapters);
        //Last one is always +3 from highest
        adapters.add(adapters.get(adapters.size() - 1) + 3);

        for (int adapter : adapters) {
            if (adapter - currentJoltage == 1) {
                oneJoltSteps++;
            } else if (adapter - currentJoltage == 3) {
                threeJoltSteps++;
            }
            currentJoltage = adapter;
        }
        System.out.printf("Part 1: %s x %s is %s\r\n", oneJoltSteps, threeJoltSteps, oneJoltSteps * threeJoltSteps);


        //PART TWO
        //ArrayList<Integer> multipliers = getMultipliers(adapters);
        //no large groups of one-step moves, so hardcode it
        ArrayList<Integer> multipliers = new ArrayList<>(List.of(0,1,1,2,4,7,13));

        oneJoltSteps = 1;
        currentJoltage = 0;
        long routes = 1L;
        //Measure perf start
        long start = System.nanoTime();
        for (int adapter : adapters) {
            if (adapter - currentJoltage == 1) {
                oneJoltSteps++;
            } else {
                routes *= multipliers.get(oneJoltSteps);
                oneJoltSteps = 1;
            }
            currentJoltage = adapter;
        }
        //Measure perf stop
        long stop = System.nanoTime();

        System.out.println("Part 2: There are " + routes + " possible routes");
        System.out.println("Route processing took: " + (stop - start) + "nanos");
    }

    private static ArrayList<Integer> getMultipliers(List<Integer> adapters) {
        // find the longest series of one jolt increments (one jolt steps can be done (+1), skipped (+2) or double skipped (+3). +4 is invalid)
        // to determine the multipliers needed for the input: Multiplier n = n-1 + n-2 + n-3 three single increments can be +1, +2, +3 \
        // four can be +1+3, +1+2+1, +1+1+2, +1+1+1+1, +2+2, +2+1+1, +3+1 (7 options ([0]1,[1]1,[2]2,[3]4,[4]7) for all preceding routes)
        // series of 5 leads to 2+4+7 = 13 multiplier, 6 = 4+7+13 = 24, etc..

        int longestSingleStepSeq = 0;
        int currentAdapter = 0;
        int countSingleSteps = 1; //shift 1 to avoid 0
        for (int adapter : adapters) {
            if (adapter - currentAdapter == 1) {
                countSingleSteps++;
            } else {
                if(countSingleSteps > longestSingleStepSeq) {
                    longestSingleStepSeq = countSingleSteps;
                }
                countSingleSteps = 1;
            }
            currentAdapter = adapter;
        }
        ArrayList<Integer> multipliers = new ArrayList<>(Arrays.asList(0,1,1));

        for(int i = 3; i <= longestSingleStepSeq; i++) {
            int newMultiplier = multipliers.get(i-3) + multipliers.get(i-2) + multipliers.get(i-1);
            multipliers.add(newMultiplier);
        }
        return multipliers;
    }
}
