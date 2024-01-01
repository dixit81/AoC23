import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

    private final static List<String> listOfNums = new ArrayList<>();

    public static void main(final String[] args) {
        int result;
        final List<String> input = readFile().stream().map(String::strip).collect(Collectors.toList());
        final char[][] grid = TestFileReaderUtility.convertTo2DArray(input);

        day3Part1(grid);
        result = listOfNums.stream().mapToInt(Integer::parseInt).sum();

        System.out.println("Part 1: " + result);

    }


    private static void day3Part1(final char[][] grid) {
        StringBuilder number = new StringBuilder();
        boolean isValid = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (Character.isDigit(grid[i][j])) {
                    number.append(grid[i][j]);
                    if (checkSurroundingPart1(grid, i, j)) {
                        isValid = true;
                    }
                } else {
                    if (isValid) {
                        listOfNums.add(number.toString());
                        isValid = false;
                    }
                    number = new StringBuilder();
                }
            }
        }
    }



    private static boolean checkSurroundingPart1(final char[][] list, final int x, final int y) {
        for (int dx = -1; dx <= 1; dx++) {
            if ((x + dx >= 0) && (x + dx < list.length)) {
                for (int dy = -1; dy <= 1; dy++) {
                    if ((y + dy >= 0) && (y + dy < list[x + dx].length) && (!(dx == 0 && dy == 0))) {
                        if (Character.toString(list[x + dx][y + dy]).matches("[^a-zA-Z0-9.]")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private static List<String> readFile() {
        try {
            return TestFileReaderUtility.fetchData("day3.txt");
        } catch (final IOException e) {
            e.getLocalizedMessage();
        }
        return null;
    }
}
