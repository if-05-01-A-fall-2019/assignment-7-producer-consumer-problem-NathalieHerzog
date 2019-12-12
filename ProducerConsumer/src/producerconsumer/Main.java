/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.concurrent.Semaphore;

/**
 *
 * @author NathalieHerzog
 */
public class Main {
     public static void main(String[] args) throws InterruptedException {
        final ProducerConsumer item = new ProducerConsumer();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    item.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    item.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
