package ru.job4j.pool;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        int size = Runtime.getRuntime().availableProcessors();
        System.out.println(size);
        ThreadPool tp = new ThreadPool();
        tp.getStatus();
        tp.shutdown();
        Thread.sleep(5000);
        System.out.println("===========");
        tp.getStatus();
    }
}
