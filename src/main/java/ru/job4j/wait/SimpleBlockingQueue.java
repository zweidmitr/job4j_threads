package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxCapacity;

    public SimpleBlockingQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void offer(T value) {
        if (maxCapacity == queue.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public synchronized boolean isEmpty() {
        boolean result = false;
        if (queue.isEmpty()) {
            result = true;
        }
        return result;
    }
}
