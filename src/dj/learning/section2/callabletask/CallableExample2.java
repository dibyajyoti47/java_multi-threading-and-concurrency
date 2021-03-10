package dj.learning.section2.callabletask;

import java.util.concurrent.*;

public class CallableExample2 {
    public static void main(String[] args) throws Exception{
        int x=11; int y=13;
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(()->x+y);
        while (!future.isDone());
        System.out.println("result is : "+future.get());
    }
}
