package dj.learning.section2.patternsearch;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PatternSearchParallelApproach {
    public static void main(String[] args) throws Exception {
        String pattern = "public";
        File dir = new File("./src/sample");
        File[] files = dir.listFiles();
        PatternFinder finder = new PatternFinder();
        ExecutorService ex = Executors.newFixedThreadPool(3);
        Map<String, Object> resutMap = new HashMap<String, Object>();
        long startTime = System.currentTimeMillis();
        for (File file: files){
            Future<List<Integer>> future = ex.submit(()->{
                List<Integer> lineNumbers = finder.find(file, pattern);
                return lineNumbers;
            });
            resutMap.put(file.getName(), future);
        }
        waitForAll(resutMap);
        for(Map.Entry<String, Object> entry: resutMap.entrySet()){
            System.out.println(pattern+ " found at : "+ entry.getValue()+ " in file " + entry.getKey());
        }
        System.out.println("parallel : "+(System.currentTimeMillis()- startTime));

    }

    private static void waitForAll(Map<String, Object> resutMap) throws Exception {
        Set<String> keys = resutMap.keySet();
        for (String key: keys) {
            Future<List<Integer>> future = (Future<List<Integer>>)resutMap.get(key);
//            while (!future.isDone()){
//                Thread.yield();
//            };
            resutMap.put(key, future.get());
        }
    }
}
