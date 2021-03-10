package dj.learning.section2;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new Thread(()->executor.execute(()-> {
            try {
                IOUtils.copy("a.txt", "c.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        })));
        executor.execute(new Thread(()->executor.execute(()-> {
            try {
                IOUtils.copy("b.txt", "d.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        })));
    }
}
