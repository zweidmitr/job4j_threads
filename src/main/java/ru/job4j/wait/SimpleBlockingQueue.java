package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) throws InterruptedException {
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }
}
