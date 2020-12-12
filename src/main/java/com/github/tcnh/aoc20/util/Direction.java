package com.github.tcnh.aoc20.util;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    N(0, -1, 0),
    NE(1, -1, 45),
    E(1, 0, 90),
    SE(1, 1, 135),
    S(0, 1, 180),
    SW(-1, 1, 225),
    W(-1, 0, 270),
    NW(-1, -1, 315);

    public final int offsetX;
    public final int offsetY;
    public final int degrees;

    private static final Map<Integer, Direction> degMap;

    static {
        degMap = new HashMap<>();
        for (Direction d : Direction.values()) {
            degMap.put(d.degrees, d);
        }
    }

    public static Direction byDegrees(int deg) {
        return degMap.get(deg);
    }


    Direction(int offsetX, int offsetY, int degrees) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.degrees = degrees;
    }


}
