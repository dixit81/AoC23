import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    private static List<Integer> winningNumbers = new ArrayList<>();
    private static List<Integer> numbersOwned;

    public static void main(final String[] args) {

        int result = 0;
        final List<String> input = readFile();

        for (final String x : input) {
            final String[] splitValues = x.split(":");

            final String[] split = splitValues[1].split("\\|");

            winningNumbers = returnWiningNumbers(split[0]);
            numbersOwned = returnNumbersOwned(split[1]);

            result += day4Part1(winningNumbers, numbersOwned);
        }

        System.out.println("Day 1: " + result);
    }

    private static List<Integer> returnNumbersOwned(final String input) {
        return Arrays.stream(input.strip().split("(?<=.)\\s+")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static List<Integer> returnWiningNumbers(final String input) {
        return Arrays.stream(input.strip().split("(?<=.)\\s+")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static int day4Part1(final List<Integer> winningNumbers, final List<Integer> numbersOwned) {

        int value = 0;

        for (final Integer win : winningNumbers) {
            if(numbersOwned.contains(win)) {
                if(value < 1) {
                    value += 1;
                } else {
                    value *= 2;
                }
            }
        }

        return value;
    }


    private static List<String> readFile() {
        try {
            return TestFileReaderUtility.fetchData("day4.txt");
        } catch (final IOException e) {
            e.getLocalizedMessage();
        }
        return null;
    }

}
