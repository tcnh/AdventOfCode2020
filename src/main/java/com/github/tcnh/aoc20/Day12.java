package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.Direction;
import com.github.tcnh.aoc20.util.InputFileReader;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

public class Day12 {
    private static final Point shipLocation = new Point();
    private static final Point wayPoint = new Point(10, 1);
    private static Direction heading = Direction.E;
    private static final List<String> input = InputFileReader.readLines("/input12.txt");

    public static void main(String[] args) {
        for (String instruction : input) {
            runP1(instruction);
        }
        System.out.println("Part 1: Manhattan distance is " + (Math.abs(shipLocation.x) + Math.abs(shipLocation.y)));

        //Reset for round 2!
        shipLocation.setLocation(0, 0);
        for (String instruction : input) {
            runP2(instruction);
        }
        System.out.println("Part 2: Manhattan distance is " + (Math.abs(shipLocation.x) + Math.abs(shipLocation.y)));
    }

    private static void runP1(String instruction) {
        String action = instruction.substring(0, 1);
        int value = Integer.parseInt(instruction.substring(1));

        Arrays.stream(Direction.values()).filter(d -> d.toString().equals(action)).forEach(d -> updateLocation(shipLocation, d, value));

        switch (action) {
            case "L":
                heading = Direction.byDegrees((heading.degrees + (360 - value)) % 360);
                break;
            case "R":
                heading = Direction.byDegrees((heading.degrees + value) % 360);
                break;
            case "F":
                updateLocation(shipLocation, heading, value);
                break;
        }
    }

    private static void runP2(String instruction) {
        String action = instruction.substring(0, 1);
        int value = Integer.parseInt(instruction.substring(1));

        Arrays.stream(Direction.values()).filter(d -> d.toString().equals(action)).forEach(d -> updateLocation(wayPoint, d, value));

        switch (action) {
            case "L":
                rotateWaypoint(360 - value);
                break;
            case "R":
                rotateWaypoint(value);
                break;
            case "F":
                shipLocation.move(shipLocation.x + (wayPoint.x * value), shipLocation.y + (wayPoint.y * value));
                break;
        }

    }

    private static void rotateWaypoint(int deg) {
        switch (deg) {
            case 0:
                break;
            case 90:
                wayPoint.move(wayPoint.y, -wayPoint.x);
                break;
            case 180:
                wayPoint.move(-wayPoint.x, -wayPoint.y);
                break;
            case 270:
                wayPoint.move(-wayPoint.y, wayPoint.x);
                break;
        }
    }

    private static void updateLocation(Point loc, Direction d, int dist) {
        switch (d) {
            case N:
                loc.move(loc.x, loc.y + dist);
                break;
            case S:
                loc.move(loc.x, loc.y - dist);
                break;
            case E:
                loc.move(loc.x + dist, loc.y);
                break;
            case W:
                loc.move(loc.x - dist, loc.y);
                break;
        }
    }
}