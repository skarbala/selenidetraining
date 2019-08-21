package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtils {
    public static List<String> getExpectedSpells() throws FileNotFoundException {
        FileReader fileReader = new FileReader(new File("src/test/resources/testData/spells.txt"));
        BufferedReader br = new BufferedReader(fileReader);
        return br.lines().collect(Collectors.toList());
    }

    public static void printPdfContent(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println("Text:" + text);
        }
        document.close();
    }
}
