package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.Direction;
import com.github.tcnh.aoc20.util.InputFileReader;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {
    private static Map<Point, Boolean> seatMap = populateSeatMap();
    private static int xMax;
    private static int yMax;

    public static void main(String[] args) {
        while (true) {
            if (!seatingChangesP1()) {
                break;
            }
        }

        System.out.println("Part 1: occupied seats: " + seatMap.keySet().stream().filter(occupied -> seatMap.get(occupied)).count());

        seatMap = populateSeatMap();
        while (true) {
            if (!seatingChangesP2()) {
                break;
            }
        }

        System.out.println("Part 2: occupied seats: " + seatMap.keySet().stream().filter(occupied -> seatMap.get(occupied)).count());
    }

    private static boolean seatingChangesP1() {
        boolean changed = false;
        Map<Point, Boolean> newSeatMap = new HashMap<>();
        for (Map.Entry<Point, Boolean> seat : seatMap.entrySet()) {
            if (!seat.getValue() && countOccupiedSurroundingSeats(seat.getKey()) == 0) {
                newSeatMap.put(seat.getKey(), true);
                changed = true;
            } else if (seat.getValue() && countOccupiedSurroundingSeats(seat.getKey()) >= 4) {
                newSeatMap.put(seat.getKey(), false);
                changed = true;
            } else {
                newSeatMap.put(seat.getKey(), seat.getValue());
            }
        }
        seatMap = newSeatMap;
        return changed;
    }

    private static boolean seatingChangesP2() {
        boolean changed = false;
        Map<Point, Boolean> newSeatMap = new HashMap<>();
        for (Map.Entry<Point, Boolean> seat : seatMap.entrySet()) {
            if (!seat.getValue()) {
                if (countVisibleOccupiedSeats(seat) == 0) {
                    newSeatMap.put(seat.getKey(), true);
                    changed = true;
                } else {
                    newSeatMap.put(seat.getKey(), seat.getValue());
                }
            } else if (countVisibleOccupiedSeats(seat) >= 5) {
                newSeatMap.put(seat.getKey(), false);
                changed = true;
            } else {
                newSeatMap.put(seat.getKey(), seat.getValue());
            }
        }
        seatMap = newSeatMap;
        return changed;
    }

    private static int countVisibleOccupiedSeats(Map.Entry<Point, Boolean> seat) {
        return (int) Arrays.stream(Direction.values())
                .filter(d -> occupiedSeatVisible(new Point(seat.getKey()), d))
                .limit(5).count();
    }

    private static boolean occupiedSeatVisible(Point p, Direction d) {
        do {
            p.move(p.x + d.offsetX, p.y + d.offsetY);
            if (seatMap.containsKey(p)) {
                return seatMap.get(p);
            }
        } while (p.x >= 0 && p.y >= 0 && p.x <= xMax && p.y <= yMax);
        return false;
    }

    private static int countOccupiedSurroundingSeats(Point seat) {
        return (int) Arrays.stream(Direction.values())
                .map(d -> new Point(seat.x + d.offsetX, seat.y + d.offsetY))
                .filter(occupiedSeat -> seatMap.containsKey(occupiedSeat) && seatMap.get(occupiedSeat))
                .limit(4).count();
    }

    private static Map<Point, Boolean> populateSeatMap() {
        if (seatMap != null) {
            seatMap.clear();
        }
        Map<Point, Boolean> result = new HashMap<>();
        List<String> input = InputFileReader.readLines("/input11.txt");
        xMax = input.get(0).length() - 1;
        yMax = input.size() - 1;

        for (int posY = 0; posY < input.size(); posY++) {
            for (int posX = 0; posX < input.get(posY).toCharArray().length; posX++) {
                if (input.get(posY).charAt(posX) == 'L') {
                    result.put(new Point(posX, posY), false);
                }
            }
        }
        return result;
    }
}