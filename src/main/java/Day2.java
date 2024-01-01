import java.io.IOException;
import java.util.*;

public class Day2 {

    private final static int MAX_RED_CUBES = 12;
    private final static int MAX_GREEN_CUBES = 13;
    private final static int MAX_BLUE_CUBES = 14;

    public static void main(final String[] args) {

        final List<String> testStringList = readFile();

        final Integer result = testStringList.stream().map(Day2::calculateDay2Part1).reduce(0, Integer::sum);
        System.out.println("Part 1: " + result);

        final Integer resultPart2 = testStringList.stream().map(Day2::calculateDay2Part2).reduce(0, Integer::sum);
        System.out.println("Part 2: " + resultPart2);

    }

    private static int calculateDay2Part2(final String line) {

        final Map<String, Integer> colourNumMap = new HashMap<>();

        int maxRedCubes = Integer.MIN_VALUE;
        int maxGreenCubes = Integer.MIN_VALUE;
        int maxBlueCubes = Integer.MIN_VALUE;

        final String[] gameIdExperimentsArray = line.split("[:;,]");
        for (int i = 1, gameIdExperimentsArrayLength = gameIdExperimentsArray.length; i < gameIdExperimentsArrayLength; i++) {
            final String[] numColorSplitArray = gameIdExperimentsArray[i].strip().split(" ");

            for (int j = 0; j < numColorSplitArray.length-1; j++) {
                colourNumMap.put(numColorSplitArray[j+1], Integer.valueOf(numColorSplitArray[j]));
            }

            if(colourNumMap.containsKey("blue") && colourNumMap.get("blue") > maxBlueCubes) {
                maxBlueCubes = colourNumMap.get("blue");
            } else if(colourNumMap.containsKey("red") && colourNumMap.get("red") > maxRedCubes) {
                maxRedCubes = colourNumMap.get("red");
            } else if(colourNumMap.containsKey("green") && colourNumMap.get("green") > maxGreenCubes) {
                maxGreenCubes = colourNumMap.get("green");
            }
        }

        return maxBlueCubes * maxGreenCubes * maxRedCubes;
    }

    private static int calculateDay2Part1(final String line) {

        final Map<String, Integer> colourNumMap = new HashMap<>();
        final List<Boolean> checkEachSet = new ArrayList<>();
        int gameId;

        final String[] gameIdExperimentsArray = line.split("[:;,]");
        final String regex = "[aA-zZ ]";
        gameId = Integer.parseInt(gameIdExperimentsArray[0].replaceAll(regex, ""));
        for (int i = 1, gameIdExperimentsArrayLength = gameIdExperimentsArray.length; i < gameIdExperimentsArrayLength; i++) {
            final String[] numColorSplitArray = gameIdExperimentsArray[i].strip().split(" ");

            for (int j = 0; j < numColorSplitArray.length-1; j++) {
                colourNumMap.put(numColorSplitArray[j+1], Integer.valueOf(numColorSplitArray[j]));
                if(colourNumMap.getOrDefault("blue", 0) <= MAX_BLUE_CUBES && colourNumMap.getOrDefault("red", 0) <= MAX_RED_CUBES && colourNumMap.getOrDefault("green", 0) <= MAX_GREEN_CUBES) {
                    checkEachSet.add(Boolean.TRUE);
                } else {
                    checkEachSet.add(Boolean.FALSE);
                }
            }
            colourNumMap.clear();
        }

        return checkEachSet.contains(Boolean.FALSE) ? 0 : gameId;

    }


    private static List<String> readFile() {
        try {
            return TestFileReaderUtility.fetchData("day2.txt");
        } catch (final IOException e) {
            e.getLocalizedMessage();
        }
        return null;
    }
}
