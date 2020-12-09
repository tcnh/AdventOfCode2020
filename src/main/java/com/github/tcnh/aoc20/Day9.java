package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 {
    static final int PREAMBLE = 25;
    static List<Long> numbers = InputFileReader.readLinesToLongs("/input9.txt");

    public static void main(String[] args) {
        int i = PREAMBLE;
        while (i < numbers.size() && isValid(numbers.get(i), i)) {
            i++;
        }

        System.out.println("Part 1: the first invalid number is: " + numbers.get(i));
        System.out.println("Part 2: Min + Max of contiguous range is: " + findContiguousRange(numbers.get(i)));
    }

    private static boolean isValid(Long number, int position) {
        for (int i = position - PREAMBLE; i < position; i++) {
            for (int offset = 1; offset <= PREAMBLE; offset++) {
                if (numbers.get(i) + numbers.get(i + offset) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static long findContiguousRange(Long invalidNumber) {
        for (int index = 0; index < numbers.size(); index++) {
            int sum = 0;
            ArrayList<Long> numbersInRange = new ArrayList<>();
            int i = index;
            while (sum <= invalidNumber) {
                sum += numbers.get(i);
                numbersInRange.add(numbers.get(i));
                if (sum == invalidNumber) {
                    return Collections.max(numbersInRange) + Collections.min(numbersInRange);
                }
                i++;
            }
        }
        return -1;
    }
}
