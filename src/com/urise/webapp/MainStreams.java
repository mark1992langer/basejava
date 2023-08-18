package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStreams {
    private static final int[] intArrays = {1,2,3,4,1,1};
    private static final List<Integer> integerList = Arrays.asList(1,2,2,2,2);

    public static void main(String[] args) {
        System.out.println(minValue());
        List<Integer> list = oddOrEven();
        System.out.println(list);
    }

    private static int minValue(){
        return Arrays.stream(MainStreams.intArrays)
                .distinct()
                .sorted()
                .reduce(0,(l, r) -> l * 10 + r);
    }

    private static List<Integer> oddOrEven() {
        final Map<Boolean, List<Integer>> oddsAndEvens = MainStreams.integerList.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        return oddsAndEvens.get(oddsAndEvens.get(false).size() % 2 != 0);
    }
}
