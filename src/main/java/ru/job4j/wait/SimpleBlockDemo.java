package ru.job4j.wait;

public class SimpleBlockDemo {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " STARTED");
                }, "MASTER"
        );
        Thread slave = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    try {
                        Integer result = queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("isNotEmpty -> alarm");
                }, "slave"
        );
        master.start();
        slave.start();
        Thread.sleep(3000);
        queue.offer(99);
        Integer result = queue.poll();
        System.out.println(result);
    }
}
