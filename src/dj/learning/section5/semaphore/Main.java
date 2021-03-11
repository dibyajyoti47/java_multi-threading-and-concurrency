package dj.learning.section5.semaphore;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new PrinterThread(1, semaphore).start();
        new PrinterThread(2, semaphore).start();
        new PrinterThread(3, semaphore).start();
        new PrinterThread(4, semaphore).start();
    }
}

class PrinterThread extends Thread {
    int id;
    Semaphore semaphore;
    public PrinterThread (int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }
    public void run () {
        try {
            semaphore.acquire();
            System.out.println("Printer "+id+" is printing...");
            Thread.sleep(500);
            semaphore.release();
        } catch (Exception e) {}

    }
}
