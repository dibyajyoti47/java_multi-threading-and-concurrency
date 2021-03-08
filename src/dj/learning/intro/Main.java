package dj.learning.intro;

public class Main {
    public static void main(String[] args) {
        new Task().start();
        for (int i=1; i<=40; i++) {
            System.out.print(i+" ");
        }
    }
}

class Task extends Thread {
    @Override
    public void run() {
        for (int i=1; i<=30;i++) {
            System.out.print(i+" ");
        }
    }
}
