package dj.learning.section5.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        new Thread(new UserMigrateTask()).start();
    }
}
class UserMigrateTask implements Runnable {
    public void run () {
        int recordCount = 220;
        int batchSize = 100;
        int nPages = (int) Math.ceil((double)recordCount/batchSize);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(nPages);
        for (int i=1; i <= nPages; i++) {
            final int pageNo = i;
            ex.submit(()-> {
                System.out.println("Migrating page : " + pageNo);
                try {Thread.sleep(2000);}catch (Exception e ) {}
                latch.countDown();
            });
        }
        ex.shutdown();
        boolean success = false;
        try {
            success = latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (success)
            System.out.println("Migration is completed.");
        else
            System.out.println("Timed out.");

    }
}
