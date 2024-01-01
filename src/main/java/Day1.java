import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Day1 {

    public static void main(final String[] args) {

        final List<String> testStringList = readFile();

        final int result = testStringList.stream().map(Day1::calculateDay1Part1).reduce(0, Integer::sum);
        final int result2 = testStringList.stream().map(Day1::calculateDay1Part2).reduce(0, Integer::sum);

        System.out.println("Result for part 1: " + result);
        System.out.println("Result for part 2: " + result2);

    }

    private static int calculateDay1Part1(final String calibrationValue) {
        final String numbersOnlyString = calibrationValue.replaceAll("[aA-zZ]", "");
        final String firstNum = String.valueOf(numbersOnlyString.charAt(0));
        final String lastNum = String.valueOf(numbersOnlyString.charAt(numbersOnlyString.length() - 1));
        return Integer.parseInt(firstNum + lastNum);
    }

    private static int calculateDay1Part2(final String calibrationValue) {
        final HashMap<String, String> replaceNums = new HashMap<>();
        replaceNums.put("one", "1");
        replaceNums.put("two", "2");
        replaceNums.put("three", "3");
        replaceNums.put("four", "4");
        replaceNums.put("five", "5");
        replaceNums.put("six", "6");
        replaceNums.put("seven", "7");
        replaceNums.put("eight", "8");
        replaceNums.put("nine", "9");

        final String firstNumber = getFirstNumber(calibrationValue, replaceNums);
        final String lastNumber = getLastNumber(calibrationValue, replaceNums);

        final String finalStringNum = firstNumber + lastNumber;

        return Integer.parseInt(finalStringNum);

    }

    private static String getLastNumber(final String calibrationValue, final HashMap<String, String> replaceNums) {
        final HashMap<String, Integer> numLocation = new HashMap<>();

        replaceNums.keySet().forEach(word -> {
            int index = calibrationValue.lastIndexOf(word);
            if (index != -1) {
                numLocation.put(word, index);
            }
        });

        replaceNums.values().forEach(digit -> {
            int index = calibrationValue.lastIndexOf(digit);
            if (index != -1) {
                numLocation.put(digit, index);
            }
        });

        int lastIndex  = Integer.MIN_VALUE;
        String value = "";
        for(String number : numLocation.keySet()) {
            if(numLocation.get(number) > lastIndex) {
                value = number;
                lastIndex = numLocation.get(number);
            }
        }

        if(value.length() != 1) {
            value = replaceNums.get(value);
        }

        return value;
    }

    private static String getFirstNumber(String calibrationValue, HashMap<String, String> replaceNums) {
        final HashMap<String, Integer> numLocation = new HashMap<>();

        replaceNums.keySet().forEach(word -> {
            int index = calibrationValue.indexOf(word);
            if (index != -1) {
                numLocation.put(word, index);
            }
        });

        replaceNums.values().forEach(digit -> {
            int index = calibrationValue.indexOf(digit);
            if (index != -1) {
                numLocation.put(digit, index);
            }
        });

        int firstIndex  = Integer.MAX_VALUE;
        String value = "";
        for(String number : numLocation.keySet()) {
            if(numLocation.get(number) < firstIndex) {
                value = number;
                firstIndex = numLocation.get(number);
            }
        }

        if(value.length() != 1) {
            value = replaceNums.get(value);
        }

        return value;
    }

    private static List<String> readFile() {
        try {
            return TestFileReaderUtility.fetchData("day1.txt");
        } catch (final IOException e) {
            e.getLocalizedMessage();
        }
        return null;
    }
}
