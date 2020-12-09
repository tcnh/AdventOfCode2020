package com.github.tcnh.aoc20;

import com.github.tcnh.aoc20.util.InputFileReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {
    static List<String> reqKeys = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    public static void main(String[] args) {
        int validP1 = 0;
        int validP2 = 0;
        String input = InputFileReader.readToString("/input4.txt");
        String[] documents = input.split("\r\n\r\n");
        for (String doc : documents) {
            Map<String, String> document = parseDocument(doc);
            if (containsAllRequiredFields(document)) {
                validP1++;
                if (allFieldsValid(document)) {
                    validP2++;
                }
            }
        }
        System.out.printf("Part 1: there are %s valid passes\n", validP1);
        System.out.printf("Part 2: there are %s valid passes", validP2);
    }

    private static Map<String, String> parseDocument(String doc) {
        String[] docInfo = doc.split("\\s");
        return Arrays.stream(docInfo).filter(kv -> kv.length() > 0)
                .map(kv -> kv.split(":"))
                .collect(Collectors.toMap(kvp -> kvp[0], kvp -> kvp[1], (a, b) -> b));
    }

    private static boolean allFieldsValid(Map<String, String> document) {
        boolean valid = true;
        for (Map.Entry<String, String> entry : document.entrySet()) {
            if (valid) {
                switch (entry.getKey()) {
                    case "byr":
                        valid = isIntegerBetween(entry.getValue(), 1920, 2002);
                        break;
                    case "iyr":
                        valid = isIntegerBetween(entry.getValue(), 2010, 2020);
                        break;
                    case "eyr":
                        valid = isIntegerBetween(entry.getValue(), 2020, 2030);
                        break;
                    case "hgt":
                        valid = isValidHeight(entry.getValue());
                        break;
                    case "hcl":
                        valid = Pattern.matches("#[0-9a-f]{6}", entry.getValue());
                        break;
                    case "ecl":
                        valid = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(entry.getValue());
                        break;
                    case "pid":
                        valid = Pattern.matches("[0-9]{9}", entry.getValue());
                        break;
                    default:
                        break;
                }
            } else {
                break;
            }
        }
        return valid;
    }

    private static boolean containsAllRequiredFields(Map<String, String> document) {
        return document.keySet().containsAll(reqKeys);
    }

    private static boolean isValidHeight(String val) {
        int min;
        int max;
        if (val.endsWith("in") || val.endsWith("cm")) {
            if (val.endsWith("in")) {
                min = 59;
                max = 76;
            } else {
                min = 150;
                max = 193;
            }
            return isIntegerBetween(val.substring(0, val.length() - 2), min, max);
        }
        return false;
    }

    private static boolean isIntegerBetween(String input, int min, int max) {
        try {
            return Integer.parseInt(input) >= min && Integer.parseInt(input) <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
