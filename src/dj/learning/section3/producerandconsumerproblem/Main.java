package dj.learning.section3.producerandconsumerproblem;

import java.util.ArrayList;
import java.util.List;

class MessageQueue {

    List<String> messages;
    int limit;

    public MessageQueue(int limit) {
        messages = new ArrayList<String>();
        this.limit = limit;
    }

    public boolean isFull() {
        return messages.size() == limit;
    }

    public boolean isEmpty() {
        return messages.size() == 0;
    }

    public synchronized void enqueue(String msg) {

        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        messages.add(msg);
        this.notify();
    }

    public synchronized String dequeue() {

        while (isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        String message = messages.remove(0);
        this.notify();
        return message;
    }

}

class ProducerThread extends Thread {
    MessageQueue queue;

    public ProducerThread(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=1; i <= 10; i++) {
            String msg = "Hello-" + i;
            queue.enqueue(msg);
            System.out.println("Produced - " + msg);
        }
    }
}

class ConsumerThread extends Thread {
    MessageQueue queue;

    public ConsumerThread(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=1; i<=10; i++) {
            String message = queue.dequeue();
            System.out.println("Consumed - " + message);
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue queue = new MessageQueue(1);
        new ProducerThread(queue).start();
        new ConsumerThread(queue).start();
    }
}