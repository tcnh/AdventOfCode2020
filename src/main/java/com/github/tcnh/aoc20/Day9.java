package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.nanoTime;

public class Day9 {
    static final int PREAMBLE = 25;
    static List<Long> numbers = InputFileReader.readLinesToLongs("/input9.txt");

    public static void main(String[] args) {
        int i = PREAMBLE;
        while (i < numbers.size() && isValid(numbers.get(i), i)) {
            i++;
        }
        System.out.println("Part 1: the first invalid number is: " + numbers.get(i));
        System.out.println("Part 2: (M1) Min + Max of contiguous range is: " + findContiguousRange(numbers.get(i)));
    }

    private static boolean isValid(Long number, int position) {
        for (int i = position - PREAMBLE; i < position; i++) {
            for (int offset = 1; offset < PREAMBLE; offset++) {
                if (numbers.get(i) + numbers.get(i + offset) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static long findContiguousRange(long invalidNumber) {
        long sum = 0L;
        ArrayList<Long> numbersInRange = new ArrayList<>();
        int i = 0;
        while (i < numbers.size()) {
            sum += numbers.get(i);
            numbersInRange.add(numbers.get(i));
            while (sum > invalidNumber) {
                sum -= numbersInRange.remove(0);
            }
            if (numbersInRange.size() > 1 && sum == invalidNumber) {
                return Collections.max(numbersInRange) + Collections.min(numbersInRange);
            }
            i++;
        }
        throw new IllegalArgumentException("No range found that sums to " + invalidNumber);
    }
}