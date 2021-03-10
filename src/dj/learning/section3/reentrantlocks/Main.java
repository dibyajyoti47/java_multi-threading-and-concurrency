package dj.learning.section3.reentrantlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

}

class Sample {
    ReadWriteLock rw_lock = new ReentrantReadWriteLock();
    private int x;
    public int getX() {
        return x;
    }
    public void setX (int x) {
        this.x = x;
    }
    public void  incr () throws Exception {
        Lock lock = rw_lock.writeLock();
        lock.lock();
        try {
            int y = getX();
            y++;
            Thread.sleep(10);
            setX(y);
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
}

class MyThread extends Thread {
    Sample obj;
    public MyThread (Sample obj) {
        this.obj = obj;
    }
    public void run () {
        try {
            obj.incr();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}