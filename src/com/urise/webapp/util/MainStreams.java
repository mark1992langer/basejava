package com.urise.webapp.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStreams {
    private static final int[] intArrays = {1,2,3,4,1,1};
    private static final List<Integer> integerList = Arrays.asList(1,2,2,2,2);

    public static void main(String[] args) {
        System.out.println(minValue(intArrays));
        List<Integer> list = oddOrEven(integerList);
        System.out.println(list);
    }

    private static int minValue(int[] values){
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((l, r) -> l * 10 + r)
                .orElseThrow();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        final Map<Boolean, List<Integer>> oddsAndEvens = integers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        return oddsAndEvens.get(oddsAndEvens.get(false).size() % 2 != 0);
    }
}
