package ru.job4j.buffer;


import ru.job4j.wait.SimpleBlockingQueue;

public class ParallelSearch {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        try {
                            System.out.println(queue.poll());
                            synchronized (LOCK) {
                                LOCK.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            synchronized (LOCK) {
                                LOCK.notifyAll();
                            }
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        producer.join();
        synchronized (LOCK) {
            LOCK.notifyAll();
        }
        System.out.println(consumer.getState());
    }
}
