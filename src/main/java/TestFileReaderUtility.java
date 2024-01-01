import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class TestFileReaderUtility {

    public static List<String> fetchData(final String file) throws IOException {
        final TestFileReaderUtility testFileReaderUtility = new TestFileReaderUtility();
        return testFileReaderUtility.readFile(file);
    }

    public String firstLineOfFile(final String file) throws IOException {
        final BufferedReader brTest = new BufferedReader(new FileReader(new File("src/main/resources/" + file)));
        return brTest.readLine();
    }

    private List<String> readFile(final String file) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/" + file)));
        return reader.lines().collect(Collectors.toList());
    }

    public static char[][] convertTo2DArray(final List<String> schema) {
        char[][] schema2D = new char[schema.get(0).length()][schema.size()];
        for (int i = 0; i < schema.size(); i++) {
            for (int j = 0; j < schema.get(i).length(); j++) {
                schema2D[i][j] = schema.get(i).charAt(j);
            }
        }
        return schema2D;
    }

}
