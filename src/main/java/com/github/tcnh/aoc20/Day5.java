package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Day5 {

    public static void main(String[] args) {

        List<String> input = InputFileReader.readLines("/input5.txt");
        int highestId = 0;
        List<Integer> seatsFound = new ArrayList<>();

        for (String line : input) {
            int[] rows = IntStream.range(0, 128).toArray();
            int[] seats = IntStream.range(0, 8).toArray();

            for (int i = 0; i < 10; i++) {
                switch (line.charAt(i)) {
                    case 'F':
                        rows = Arrays.copyOfRange(rows, 0, rows.length / 2);
                        break;
                    case 'B':
                        rows = Arrays.copyOfRange(rows, rows.length / 2, rows.length);
                        break;
                    case 'R':
                        seats = Arrays.copyOfRange(seats, seats.length / 2, seats.length);
                        break;
                    case 'L':
                        seats = Arrays.copyOfRange(seats, 0, seats.length / 2);
                        break;
                }
            }
            int seatId = rows[0] * 8 + seats[0];
            seatsFound.add(seatId);
            if (seatId > highestId) {
                highestId = seatId;
            }
        }
        Collections.sort(seatsFound);
        int seatToCheck = 0;
        int missing = 0;
        for (int s = seatsFound.get(0); s <= highestId; s++) {
            if (seatsFound.get(seatToCheck) != s) {
                missing = s;
                break;
            }
            seatToCheck++;
        }
        System.out.println("Part 1: Highest seatID is: " + highestId);
        System.out.println("Part 2: My seatID is: " + missing);
    }

}
