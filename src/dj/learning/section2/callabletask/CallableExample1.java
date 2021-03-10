package dj.learning.section2.callabletask;

import java.util.concurrent.*;

public class CallableExample1 {
    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(new MyTask(10, 12));
        while (!future.isDone());
        System.out.println("result is : "+future.get());
    }
}

class MyTask implements Callable<Integer> {
    private int x;
    private int y;
    public MyTask(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer call() throws Exception {
        return x+y;
    }
}