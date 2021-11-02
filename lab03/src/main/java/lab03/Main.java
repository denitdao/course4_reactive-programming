package lab03;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Main {

    public static void main(String[] args) {
        List<Integer> ints1 = new Random().ints(20, -10, 10).boxed().collect(Collectors.toList());
        List<Integer> ints2 = new Random().ints(20, -10, 10).boxed().collect(Collectors.toList());
        List<Integer> ints3 = new Random().ints(40, -20, 20).boxed().collect(Collectors.toList());
        log.info("Integers 1:\n{}", ints1);
        log.info("Integers 2:\n{}", ints2);
        log.info("Integers 3:\n{}", ints3);
        log.warn("Result - {}", doActions(ints1, ints2, ints3));
    }

    private static List<Integer> doActions(List<Integer> ints1, List<Integer> ints2, List<Integer> ints3) {
        CompletableFuture<List<Integer>> ft1 = CompletableFuture.supplyAsync(
                () -> {
                    List<Integer> result = ints1.stream()
                                                .map(i -> i * 3)
                                                .collect(Collectors.toList());
                    log.info("ft1 - {}", result);
                    return result;
                }
        );
        CompletableFuture<List<Integer>> ft2 = CompletableFuture.supplyAsync(
                () -> {
                    List<Integer> result = ints2.stream()
                                                .filter(i -> i % 2 == 0)
                                                .collect(Collectors.toList());
                    log.info("ft2 - {}", result);
                    return result;
                }
        );
        CompletableFuture<List<Integer>> ft3 = CompletableFuture.supplyAsync(
                () -> {
                    double average = ints3.stream().mapToInt(i -> i).average().orElse(0.);
                    List<Integer> result = ints3.stream()
                                                .filter(i -> i > average - 2 && i < average + 2)
                                                .collect(Collectors.toList());
                    log.info("Average - {}", average);
                    log.info("ft3 - {}", result);
                    return result;
                }
        );

        CompletableFuture<List<Integer>> calculate =
                CompletableFuture.allOf(ft1, ft2, ft3)
                                 .thenApply(ignored -> {
                                     List<Integer> list = ft1.join();
                                     list.addAll(ft2.join());
                                     list.removeAll(ft3.join());
                                     return list.stream().sorted().collect(Collectors.toList());
                                 });

        return calculate.join();
    }
}
