package com.github.tcnh.aoc20.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputFileReader {
    public static List<Long> readLinesToLongs(String resourceFile) {
        try {
            return IOUtils.readLines(input(resourceFile), "UTF-8").stream().map(Long::parseLong).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Integer> readLinesToIntegers(String resourceFile) {
        try {
            return IOUtils.readLines(input(resourceFile), "UTF-8").stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<String> readLines(String resourceFile) {
        try {
            return IOUtils.readLines(input(resourceFile), "UTF-8");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Integer> readCommaSeparatedNumbers(String resourceFile) {
        try {
            String[] strings = IOUtils.toString(input(resourceFile), StandardCharsets.UTF_8).split("\\s*,\\s*");
            return Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static String readToString(String resourceFile) {
        try {
            return IOUtils.toString(input(resourceFile), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "";
        }
    }

    private static InputStream input(String resourceFile) {
        InputStream input = InputFileReader.class.getResourceAsStream(resourceFile);
        if (input == null) {
            throw new IllegalArgumentException("file not found! " + resourceFile);
        }
        return input;
    }
}
