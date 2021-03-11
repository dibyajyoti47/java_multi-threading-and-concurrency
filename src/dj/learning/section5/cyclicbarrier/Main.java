package dj.learning.section5.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(2);
        new A(cb).start();
        try{Thread.sleep(5000);}catch (Exception e) {}
        new B(cb).start();
    }
}

class A extends Thread {
    CyclicBarrier cb;
    public A(CyclicBarrier cb) {
        this.cb = cb;
    }
    public void run () {
        try {cb.await();}catch (Exception e){};
        System.out.println("A begins.");
    }
}

class B extends Thread {
    CyclicBarrier cb;
    public B(CyclicBarrier cb) {
        this.cb = cb;
    }
    public void run () {
        try {cb.await();}catch (Exception e){};
        System.out.println("B begins.");
    }
}
