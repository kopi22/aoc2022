package day4;

import java.util.List;

import utils.InputReader;

public class DayFour {

    private static class Interval {
        private final int start, end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public boolean fullyContains(Interval other) {
            return other.getStart() <= this.getStart() && this.getEnd() <= other.getEnd();
        }

        public boolean overlaps(Interval other) {
            return !(this.getEnd() < other.getStart() || other.getEnd() < this.getStart());
        }
    }
    
    public static void main(String[] args) {
        var lines = InputReader.loadInputFile(args[0]);

        part1(lines);
        part2(lines);
    }


    public static void part1(List<String> input) {
        System.out.println("## PART 1");

        var numRedundantAssignments = 0;
        for (var line : input) {
            var intervals = line.split("[^\\w]");
            var intervalA = new Interval(Integer.parseInt(intervals[0]), Integer.parseInt(intervals[1]));
            var intervalB = new Interval(Integer.parseInt(intervals[2]), Integer.parseInt(intervals[3]));
            if (intervalA.fullyContains(intervalB) || intervalB.fullyContains(intervalA)) {
                numRedundantAssignments += 1;
            }
        }

        System.out.println("Redundant assignments: %s".formatted(numRedundantAssignments));
    }

    public static void part2(List<String> input) {
        System.out.println("## PART 2");

        var numOverlappingAssignments = 0;
        for (var line : input) {
            var intervals = line.split("[-,]");
            var intervalA = new Interval(Integer.parseInt(intervals[0]), Integer.parseInt(intervals[1]));
            var intervalB = new Interval(Integer.parseInt(intervals[2]), Integer.parseInt(intervals[3]));
            if (intervalA.overlaps(intervalB)) {
                numOverlappingAssignments += 1;
            }
        }

        System.out.println("Overlapping assignments: %s".formatted(numOverlappingAssignments));
    }
}
