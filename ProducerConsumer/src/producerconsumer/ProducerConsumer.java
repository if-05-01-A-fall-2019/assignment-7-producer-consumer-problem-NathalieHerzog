/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import static java.lang.Math.random;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Semaphore;
/**
 *
 * @author NathalieHerzog
 */
public class ProducerConsumer {
    private final Semaphore consumerSemaphore = new Semaphore(0);
    private final Semaphore producerSemaphore = new Semaphore(N);

    private static final int N = 10;
    private static int count = 0;
    List<Integer> items = new LinkedList<>();
    Stack buffer = new Stack<Integer>();
    Random random = new Random();
    final int MAX_ITEMS = 5;

    public void producer() throws InterruptedException {
        while(true) {
            synchronized(this) {
                Integer item = random.nextInt(5000);
                if(count == MAX_ITEMS) {
                    wait();
                }
                
                System.out.println("Produced " + item + " " + count + " time(s).");
                buffer.push(item);
                count++;
                if(count == 1)
                {
                    notify();
                }
                
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        int item;
        while (true) {
            consumerSemaphore.acquire();
            item = items.remove(items.size() - 1);
            count--;
            ProducerConsumer.this.consume(item);
            System.out.format("Consumed! %d \n", item);
            producerSemaphore.release();
        }
    }

    private void consume(int item) throws InterruptedException {
        Thread.sleep(10000);
    }    
}
