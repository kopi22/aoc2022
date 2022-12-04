package day1;

import java.util.PriorityQueue;

import utils.InputReader;

public class DayOneCalories {
    public static void main(String[] args) {
        var lines = InputReader.loadInputFile(args[0]);

        var topThreeCalories = new PriorityQueue<Integer>();

        var currentElfCalories = 0;
        for (var line : lines) {
            if (!line.equals("")) {
                currentElfCalories += Integer.parseInt(line);
            } else {
                topThreeCalories.add(currentElfCalories);
                if (topThreeCalories.size() > 3) {
                    topThreeCalories.poll();
                }
                currentElfCalories = 0;
            }
        }
        topThreeCalories.add(currentElfCalories);
        if (topThreeCalories.size() > 3) {
            topThreeCalories.poll();
        }

        var maxCalories = 0;
        var sumOfTopThreeCalories = 0;

        while (!topThreeCalories.isEmpty()) {
            var calories = topThreeCalories.poll();
            maxCalories = calories;
            sumOfTopThreeCalories += calories;
        }


        System.out.println(String.format("Max calories: %d", maxCalories));
        System.out.println(String.format("Sum of top three calories: %d", sumOfTopThreeCalories));
    }
}