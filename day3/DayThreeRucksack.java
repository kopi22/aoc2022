package day3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import utils.InputReader;

public class DayThreeRucksack {
    public static void main(String[] args) {
        var lines = InputReader.loadInputFile(args[0]);

        part1(lines);
        part2(lines);
    }

    private static int getItemPriority(char item) {
        return 1 + (((int) Character.toLowerCase(item)) - ((int) 'a')) + (Character.isUpperCase(item) ? 26 : 0);
    }

    public static void part1(List<String> input) {
        System.out.println("## PART 1");
        
        var sumOfPriorities = 0;

        for (var rucksack : input) {
            var itemsInFirstCompartment = new HashSet<Character>();
            for (int i = 0; i < rucksack.length() / 2; i++) {
                var item = rucksack.charAt(i);
                itemsInFirstCompartment.add(item);
            }
            for (int i = rucksack.length() / 2; i < rucksack.length(); i++) {
                var item = rucksack.charAt(i);
                if (itemsInFirstCompartment.contains(item)) {
                    sumOfPriorities += getItemPriority(item);
                    break;
                }
            }
        }

        System.out.println("Sum of priorities: %d".formatted(sumOfPriorities));
    }

    public static void part2(List<String> input) {
        System.out.println("## PART 2");

        var sumOfPriorities = 0;

        assert input.size() % 3 == 0;

        for (int i = 0; i < input.size() / 3; i++) {
            Set<Character> itemsInCommon = new HashSet<Character>();
            
            for (int j = 0; j < 3; j++) {
                var rucksack = input.get(3*i + j)
                                    .chars()
                                    .mapToObj(c -> (char) c)
                                    .collect(Collectors.toSet());

                if (j == 0) {
                    itemsInCommon = rucksack;
                } else {
                    itemsInCommon.retainAll(rucksack);
                }
                
            }
            
            assert itemsInCommon.size() == 1;
            
            for (var item : itemsInCommon) {
                sumOfPriorities += getItemPriority(item);
            }
        }

        System.out.println("Sum of badge priorities: %d".formatted(sumOfPriorities));
    }
}
