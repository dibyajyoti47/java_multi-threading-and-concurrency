package dj.learning.section2.patternsearch;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PatterSearchSerialApproach {
    public static void main(String[] args) {
        String pattern = "public";
        File dir = new File("./src/sample");
        File[] files = dir.listFiles();
        PatternFinder finder = new PatternFinder();
        long startTime = System.currentTimeMillis();
        for (File file: files){
            List<Integer> lineNumbers = finder.find(file, pattern);
            if (!lineNumbers.isEmpty()) {
                System.out.println(pattern+" found at "+lineNumbers+" in the file."+ file.getName());
            }
        }
        System.out.println("serail : "+(System.currentTimeMillis()- startTime));

    }
}
