package dj.learning.section2.patternsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PatternFinder {
    public List<Integer> find(File file, String pattern) {
        List<Integer> linenNumbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int lineNo=1;
            while ((line = br.readLine()) != null) {
                if(line.contains(pattern)){
                    linenNumbers.add(lineNo);
                }
                lineNo++;
            }
        }catch (Exception e){}
        try {
            Thread.sleep(1000);
        }catch (Exception e){}
        return linenNumbers;
    }
}
