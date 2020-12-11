package com.github.tcnh.aoc20.util;

public enum Direction {
    N(0, -1),
    NE(1, -1),
    E(1, 0),
    SE(1, 1),
    S(0, 1),
    SW(-1, 1),
    W(-1, 0),
    NW(-1, -1);

    public final int offsetX;
    public final int offsetY;

    Direction(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
}
