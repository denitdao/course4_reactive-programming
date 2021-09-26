package ua.denitdao.kpi.reactive.lab01;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Main {

    public static void main(String[] args) {
        int[] ints = new Random().ints(20, -40, 40).toArray();
        log.info("Integers:\n{}", ints);

        log.info("1  - Найти два наибольших элемента массива\n{}", getTwoMaxArrayNumbers(Arrays.stream(ints)));
        log.info("5  - Посчитать количество элементов больше нуля\n{}", countBiggerThanZero(Arrays.stream(ints)));
        log.info("8  - Найти максимальный элемент, значение и индекс\n{}", getMaxInfo(ints));
        log.info("13 - Найти первый положительный элемент\n{}", getFirstPositive(Arrays.stream(ints)));
        log.info("17 - Вывести элементы, значения которых равны значениям других элементов\n{}", getRepeatingNumbers(Arrays.stream(ints)));
    }

    private static int[] getTwoMaxArrayNumbers(IntStream intStream) {
        return intStream.boxed()
                .sorted(Collections.reverseOrder())
                .limit(2)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static long countBiggerThanZero(IntStream intStream) {
        return intStream.filter(i -> i > 0)
                .count();
    }

    private static MaxInfo getMaxInfo(int[] array) {
        return IntStream.range(0, array.length)
                .mapToObj(i -> new MaxInfo(array[i], i))
                .max(Comparator.comparing(MaxInfo::getValue))
                .orElse(null);
    }

    private static int getFirstPositive(IntStream intStream) {
        return intStream.filter(i -> i > 0)
                .findFirst()
                .orElse(-1);
    }

    private static int[] getRepeatingNumbers(IntStream intStream) {
        return intStream.boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(p -> p.getValue() > 1)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
