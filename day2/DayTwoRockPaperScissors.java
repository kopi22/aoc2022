package day2;

import java.util.List;

import utils.InputReader;

public class DayTwoRockPaperScissors {
    private enum RPS { 
        ROCK (1), 
        PAPER (2), 
        SCISSORS (3);
        
        private final int value;

        RPS(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        int computeGameScore(RPS opponentsChoice) {
            int gameOutcome = 0;
            
            if (opponentsChoice == this) {
                gameOutcome = 3;
            } 
            else if (opponentsChoice == getLosingOpponentChoice()) {
                gameOutcome = 6;
            }

            return gameOutcome + getValue();
        }

        RPS getLosingOpponentChoice() {
            return switch (this) {
                case ROCK     -> SCISSORS;
                case PAPER    -> ROCK;
                case SCISSORS -> PAPER;
            };
        }

        RPS getDrawingOpponentChoice() {
            return this;
        }

        RPS getWinningOpponentChoice() {
            return getLosingOpponentChoice().getLosingOpponentChoice();
        }

        static RPS fromString(String choice) {
            switch (choice) {
                case "A":
                case "X":
                    return ROCK;
                case "B":
                case "Y":
                    return PAPER;
                case "C":
                case "Z":
                    return SCISSORS;
                default:
                    throw new IllegalArgumentException("Ivalid game choice, must be one of [A/B/C/X/Y/Z]");
            }
        }
    }

    public static void main(String[] args) {
        var lines = InputReader.loadInputFile(args[0]);

        part1(lines);
        part2(lines);
    }

    public static void part1(List<String> input) {
        System.out.println("## PART 1");

        var score = 0;
        for (var line : input) {
            var choices = line.split(" ");
            var opponentsChoice = RPS.fromString(choices[0]);
            var myChoice = RPS.fromString(choices[1]);
            score += myChoice.computeGameScore(opponentsChoice);
        }

        System.out.println(String.format("My final score: %d", score));
    }

    public static void part2(List<String> input) {
        System.out.println("## PART 2");

        var score = 0;
        for (var line : input) {
            var choices = line.split(" ");
            var opponentsChoice = RPS.fromString(choices[0]);
            var myChoice = switch (choices[1]) {
                case "X" -> opponentsChoice.getLosingOpponentChoice();
                case "Y" -> opponentsChoice.getDrawingOpponentChoice();
                case "Z" -> opponentsChoice.getWinningOpponentChoice();
                default -> throw new IllegalArgumentException("Not a valid strategy choice");
            };
            score += myChoice.computeGameScore(opponentsChoice);
        }

        System.out.println(String.format("My final score: %d", score));
    }
}