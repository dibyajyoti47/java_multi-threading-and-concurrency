package dj.learning.section3.synchronization;

public class Main {
    public static void main(String[] args) throws Exception {
        Sample sample = new Sample();
        Thread t1 = new MyThread(sample);
        Thread t2 = new MyThread(sample);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(sample.getX());
    }
}

class Sample {
    private int x;
    public int getX() {
        return x;
    }
    public void setX (int x) {
        this.x = x;
    }
    public void  incr () throws Exception {
            int y = getX();
            y++;
            Thread.sleep(10);
            setX(y);
    }
}

class MyThread extends Thread {
    Sample obj;
    public MyThread (Sample obj) {
        this.obj = obj;
    }
    public void run () {
        try {
            synchronized (obj) {
                obj.incr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}