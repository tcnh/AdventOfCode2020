package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day6 {
    public static void main(String[] args) {
        String input = InputFileReader.readToString("/input6.txt");
        String[] groups = input.split("\r\n\r\n");
        int sumP1 = 0;
        int sumP2 = 0;
        for (String group : groups) {
            Set<Character> positiveAnswers = new HashSet<>();
            String[] persons = group.split("\r\n");
            Arrays.stream(persons).map(String::toCharArray).forEach(personPosAnswers -> {
                for (char answer : personPosAnswers) {
                    positiveAnswers.add(answer);
                }
            });
            sumP1 += positiveAnswers.size();

            for (char c : persons[0].toCharArray()) {
                if (Arrays.stream(persons).allMatch(person -> person.contains(String.valueOf(c)))) {
                    sumP2++;
                }
            }
        }
        System.out.println("Part 1: the sum of yesses is: " + sumP1);
        System.out.println("Part 2: the sum of mutual yesses is: " + sumP2);
    }

}
