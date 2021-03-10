package dj.learning.section2;

public class StoppingThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(;;){
                if (Thread.interrupted()) {
                    System.out.print("Breaking....");break;
                }
                System.out.print("T");
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
