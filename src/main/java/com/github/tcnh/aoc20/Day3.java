package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.awt.*;
import java.util.List;


public class Day3 {
    static List<String> lines = InputFileReader.readLines("/input3.txt");
    public static void main(String[] args) {

        System.out.printf("Part 1: Encountered %s trees\r\n", treesEncountered(3,1));
        System.out.printf("Part 2: Answer: %s\r\n", treesEncountered(1,1) *
                        treesEncountered(3,1) *
                        treesEncountered(5,1) *
                        treesEncountered(7,1) *
                        treesEncountered(1,2));
    }

    public static int treesEncountered(int right, int down) {
        Point pos = new Point();
        int trees = 0;
        while(pos.getY() < lines.size()) {
            int x = pos.x + right >= lines.get(pos.y).length()
                    ? pos.x + right - lines.get(pos.y).length()
                    : pos.x + right;
            int y = pos.y + down;

            pos.move(x, y);
            if(lines.size() > y && lines.get(y).charAt(x) == '#') {
                trees++;
            }
        }
        return trees;
    }
}
