package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtils {
    public static List<String> getExpectedSpells() throws FileNotFoundException {
        FileReader fileReader = new FileReader(new File("src/test/resources/testData/spells.txt"));
        BufferedReader br = new BufferedReader(fileReader);
        return br.lines().collect(Collectors.toList());
    }
}
